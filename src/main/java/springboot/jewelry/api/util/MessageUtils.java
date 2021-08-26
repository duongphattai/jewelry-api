package springboot.jewelry.api.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageUtils {
    private String message;

    public MessageUtils(String message) {
        this.message = message;
    }
}
