package springboot.jewelry.api.customer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import springboot.jewelry.api.customer.util.CustomerGender;
import springboot.jewelry.api.customer.validation.annotation.ConfirmPassword;
import springboot.jewelry.api.util.DateUtils;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@ConfirmPassword
public class CustomerUpdateDto {
    @NotBlank(message = "{Customer.username.NotBlank}")
    @Size(min = 8, max = 30, message = "{Customer.username.Size}")
    private String password;

    @NotBlank
    private String confirmPassword;

    @NotBlank(message = "{Customer.fullName.NotBlank}")
    @Size(min = 3, max = 50, message = "{Customer.fullName.Size}")
    private String fullName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CustomerGender gender;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.BIRTHDAY_FORMAT)
    private LocalDate birthday;

    @NotBlank(message = "{Customer.address.NotBlank}")
    @Size(min = 20, max = 100, message = "{Customer.address.Size}")
    private String address;
}
