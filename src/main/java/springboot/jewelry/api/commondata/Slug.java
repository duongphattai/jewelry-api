package springboot.jewelry.api.commondata;

import com.github.slugify.Slugify;
import com.ibm.icu.text.Transliterator;

import java.io.InputStream;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

public class Slug {

    private static final String BUILTIN_REPLACEMENTS_FILENAME = "replacements.properties";
    private static final Properties REPLACEMENTS = new Properties();

    private final static String ASCII = "Cyrillic-Latin; Any-Latin; Latin-ASCII; [^\\p{Print}] Remove; ['\"] Remove; Any-Lower";
    private final static String EMPTY = "";
    private static final String UNDERSCORE = "_";
    private static final String HYPHEN = "-";

    private final static Pattern PATTERN_NORMALIZE_NON_ASCII = Pattern.compile("[^\\p{ASCII}]+");
    private final static Pattern PATTERN_NORMALIZE_HYPHEN_SEPARATOR = Pattern.compile("[\\W\\s+]+");
    private final static Pattern PATTERN_NORMALIZE_UNDERSCORE_SEPARATOR = Pattern.compile("[[^a-zA-Z0-9\\-]\\s+]+");
    private final static Pattern PATTERN_NORMALIZE_TRIM_DASH = Pattern.compile("^-|-$");

    private final Map<String, String> customReplacements = new HashMap<String, String>();
    private final Map<Character, String> builtinReplacements = new HashMap<Character, String>();

    private boolean transliterator = false;
    private boolean underscoreSeparator = false;
    private boolean lowerCase = true;

    public Slug() {
        loadReplacements(BUILTIN_REPLACEMENTS_FILENAME);
        createPatternCache();
    }

    private Slug loadReplacements(final String resourceFileName) {
        if (!REPLACEMENTS.isEmpty()) {
            return this;
        }

        try {
            final InputStream replacementsStream = getClass().getClassLoader().getResourceAsStream(resourceFileName);
            REPLACEMENTS.load(replacementsStream);
            replacementsStream.close();
            return this;
        } catch (Exception e) {
            throw new RuntimeException(String.format("Resource '%s' not loaded!", resourceFileName), e);
        }
    }

    private void createPatternCache() {
        if (!builtinReplacements.isEmpty()) {
            return;
        }

        REPLACEMENTS.entrySet().forEach(replacement -> addReplacement(replacement));
    }

    private void addReplacement(Map.Entry<Object, Object> e) {
        if (isValidReplacement(e)) {
            throw new IllegalArgumentException("Builtin replacements can only be characters");
        }
        builtinReplacements.put(e.getKey().toString().charAt(0), e.getValue().toString());
    }

    private boolean isValidReplacement(Map.Entry<Object, Object> replacement) {
        return replacement.getKey().toString().length() > 1;
    }

    public String slugify(final String text) {
        String input = text;
        if (isNullOrBlank(input)) {
            return EMPTY;
        }

        input = input.trim();
        input = customReplacements(input);
        input = builtInReplacements(input);

        if (transliterator) {
            input = transliterate(input);
        } else {
            input = normalize(input);
        }

        if (lowerCase) {
            input = input.toLowerCase();
        }

        return input;
    }

    private static boolean isNullOrBlank(final String string) {
        return string == null || string.trim().isEmpty();
    }

    private String customReplacements(String input) {
        Map<String, String> customReplacements = getCustomReplacements();
        for (Map.Entry<String, String> entry : customReplacements.entrySet()) {
            input = input.replace(entry.getKey(), entry.getValue());
        }

        return input;
    }

    public Map<String, String> getCustomReplacements() {
        return customReplacements;
    }

    private String builtInReplacements(String input) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char s : input.toCharArray()) {
            if (builtinReplacements.containsKey(s)) {
                stringBuilder.append(builtinReplacements.get(s));
            } else {
                stringBuilder.append(s);
            }
        }
        return stringBuilder.toString();
    }

    private String transliterate(final String input) {
        String text = Transliterator.getInstance(ASCII).transliterate(input);
        text = matchAndReplace(text);
        return text;
    }

    private String normalize(final String input) {
        String text = Normalizer.normalize(input, Normalizer.Form.NFKD);
        text = matchAndReplace(text);
        return text;
    }

    private String matchAndReplace(final String input) {
        String text = PATTERN_NORMALIZE_NON_ASCII.matcher(input).replaceAll(EMPTY);
        text = underscoreSeparator ? PATTERN_NORMALIZE_UNDERSCORE_SEPARATOR.matcher(text).replaceAll(UNDERSCORE) :
                PATTERN_NORMALIZE_HYPHEN_SEPARATOR.matcher(text).replaceAll(HYPHEN);
        text = PATTERN_NORMALIZE_TRIM_DASH.matcher(text).replaceAll(EMPTY);

        return text;
    }
}
