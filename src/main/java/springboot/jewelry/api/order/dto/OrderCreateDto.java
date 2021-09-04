package springboot.jewelry.api.order.dto;

import lombok.Getter;
import lombok.Setter;
import springboot.jewelry.api.order.util.OrderStatus;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;

@Getter
@Setter
public class OrderCreateDto {

    @NotBlank(message = "{order.full-name.not-blank}")
    @Size(min = 3, max = 50, message = "{order.full-name.size}")
    private String fullName;

    @NotBlank(message = "{order.address.not-blank}")
    @Size(min = 20, max = 100, message = "{order.address.size}")
    private String address;

    @NotBlank(message = "{order.phone-number.not-blank}")
    @Pattern(regexp = "(^((?=(0))[0-9]{10})$)", message = "{order.phone-number.pattern}")
    private String phoneNumber;

    @NotBlank(message = "{order.email.not-blank}")
    @Email(message = "{order.email.format}")
    private String email;

    @Size(max = 500, message = "{order.description.size}")
    private String content;
}
