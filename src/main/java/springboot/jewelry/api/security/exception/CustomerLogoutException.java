package springboot.jewelry.api.security.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class CustomerLogoutException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    private final String customer;
    private final String message;

    public CustomerLogoutException(String customer, String message) {
        super(String.format("Couldn't log out device [%s]: [%s])", customer, message));
        this.customer = customer;
        this.message = message;
    }
}
