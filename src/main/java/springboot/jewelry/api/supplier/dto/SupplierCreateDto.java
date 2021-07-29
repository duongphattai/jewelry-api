package springboot.jewelry.api.supplier.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierCreateDto {

    private String code;

    private String name;

    private String phoneNumber;

    private String email;

    private String address;

    private String logo;
}
