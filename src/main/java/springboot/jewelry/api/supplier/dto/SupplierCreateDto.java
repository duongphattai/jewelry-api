package springboot.jewelry.api.supplier.dto;


import lombok.Getter;
import lombok.Setter;
import springboot.jewelry.api.supplier.validation.annotation.UniqueSupplierCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class SupplierCreateDto {

    @UniqueSupplierCode
    private String code;

    @NotBlank(message = "{supplier.name.not-blank}")
    private String name;

    @Pattern(regexp = "(^$|[0-9]{10})", message = "{supplier.phone-number.pattern}")
    private String phoneNumber;

    @Email(message = "{supplier.email.email}")
    private String email;

    private String address;

    private String logo;
}
