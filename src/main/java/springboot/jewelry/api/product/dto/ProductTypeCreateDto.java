package springboot.jewelry.api.product.dto;

import lombok.Getter;
import lombok.Setter;
import springboot.jewelry.api.product.validation.annotation.UniqueProductTypeCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ProductTypeCreateDto {

    @NotBlank(message = "{ProductType.code.NotBlank}")
    @Size(min = 3, max = 10, message = "{ProductType.code.Size}")
    @UniqueProductTypeCode
    private String code;

    @NotBlank(message = "{ProductType.name.NotBlank}")
    @Size(min = 1, max = 30, message = "{ProductType.name.Size}")
    private String name;

}
