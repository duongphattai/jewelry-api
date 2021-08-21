package springboot.jewelry.api.security.cache;

import net.jodah.expiringmap.ExpiringMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springboot.jewelry.api.security.event.OnCustomerLogoutSuccessEvent;
import springboot.jewelry.api.security.jwt.JwtProvider;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class LoggedOutJwtTokenCache {

    private static final Logger logger = LoggerFactory.getLogger(LoggedOutJwtTokenCache.class);

    private ExpiringMap<String, OnCustomerLogoutSuccessEvent> tokenEventMap;
    private JwtProvider tokenProvider;

    @Autowired
    public LoggedOutJwtTokenCache(JwtProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
        this.tokenEventMap = ExpiringMap.builder()
                .variableExpiration()
                .maxSize(1000)
                .build();
    }

    public void markLogoutEventForToken(OnCustomerLogoutSuccessEvent event) {
        String token = event.getToken();
        if (tokenEventMap.containsKey(token)) {
            logger.info(String.format("Log out token for user [%s] is already present in the cache", event.getCustomerEmail()));

        } else {
            Date tokenExpiryDate = tokenProvider.getTokenExpiryFromJWT(token);
            long ttlForToken = getTTLForToken(tokenExpiryDate);
            logger.info(String.format("Logout token cache set for [%s] with a TTL of [%s] seconds. Token is due expiry at [%s]", event.getCustomerEmail(), ttlForToken, tokenExpiryDate));
            tokenEventMap.put(token, event, ttlForToken, TimeUnit.SECONDS);
        }
    }

    public OnCustomerLogoutSuccessEvent getLogoutEventForToken(String token) {
        return tokenEventMap.get(token);
    }

    private long getTTLForToken(Date date) {
        long secondAtExpiry = date.toInstant().getEpochSecond();
        long secondAtLogout = Instant.now().getEpochSecond();
        return Math.max(0, secondAtExpiry - secondAtLogout);
    }
}
