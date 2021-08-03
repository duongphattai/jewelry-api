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

    @NotBlank(message = "{Supplier.name.NotBlank}")
    private String name;

    @Pattern(regexp = "(^$|[0-9]{10})", message = "{Supplier.phoneNumber.Pattern}")
    private String phoneNumber;

    @Email(message = "{Supplier.email.Email}")
    private String email;

    private String address;

    private String logo;
}
