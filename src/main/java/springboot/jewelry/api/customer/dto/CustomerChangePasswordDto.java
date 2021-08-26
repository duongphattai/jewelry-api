package springboot.jewelry.api.customer.dto;

import lombok.Getter;
import lombok.Setter;
import springboot.jewelry.api.customer.validation.annotation.ConfirmPassword;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@ConfirmPassword
public class CustomerChangePasswordDto {

    @NotBlank(message = "{customer.password.not-blank}")
    @Size(min = 8, max = 30, message = "{customer.password.size}")
    private String password;

    @NotBlank
    private String confirmPassword;
}
