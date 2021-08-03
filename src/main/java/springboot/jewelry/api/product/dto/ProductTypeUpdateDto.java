package springboot.jewelry.api.product.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ProductTypeUpdateDto {

    @NotBlank(message = "{ProductType.name.NotBlank}")
    @Size(min = 1, max = 30, message = "{ProductType.name.Size}")
    private String name;
}
