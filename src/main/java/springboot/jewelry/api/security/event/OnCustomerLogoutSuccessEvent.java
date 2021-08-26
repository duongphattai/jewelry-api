package springboot.jewelry.api.security.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
import springboot.jewelry.api.security.dto.LogoutDto;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
public class OnCustomerLogoutSuccessEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1L;
    private final String customerEmail;
    private final String token;
    private final transient LogoutDto logoutDto;
    private final Date eventTime;

    public OnCustomerLogoutSuccessEvent(String customerEmail, String token, LogoutDto logoutDto) {
        super(customerEmail);
        this.customerEmail = customerEmail;
        this.token = token;
        this.logoutDto = logoutDto;
        this.eventTime = Date.from(Instant.now());
    }
}
