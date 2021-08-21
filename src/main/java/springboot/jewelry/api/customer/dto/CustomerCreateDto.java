package springboot.jewelry.api.customer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import springboot.jewelry.api.customer.util.CustomerGender;
import springboot.jewelry.api.customer.validation.annotation.*;
import springboot.jewelry.api.util.DateUtils;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@ConfirmPassword
public class CustomerCreateDto {

    @Email(message = "{customer.email.format}")
    @UniqueEmail
    private String email;

    @NotBlank(message = "{customer.password.not-blank}")
    @Size(min = 8, max = 30, message = "{customer.password.size}")
    private String password;

    @NotBlank
    private String confirmPassword;

    @NotBlank(message = "{customer.full-name.not-blank}")
    @Size(min = 3, max = 50, message = "{customer.full-name.size}")
    private String fullName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CustomerGender gender;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.BIRTHDAY_FORMAT)
    private LocalDate birthday;

    @NotBlank(message = "{customer.phone-number.not-blank}")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "{customer.phone-number.pattern}")
    @UniquePhoneNumber
    private String phoneNumber;

    @NotBlank(message = "{customer.address.not-blank}")
    @Size(min = 20, max = 100, message = "{customer.address.size}")
    private String address;

}
