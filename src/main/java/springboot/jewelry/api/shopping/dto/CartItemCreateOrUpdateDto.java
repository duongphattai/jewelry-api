package springboot.jewelry.api.shopping.dto;

import lombok.Getter;
import lombok.Setter;
import springboot.jewelry.api.shopping.validation.annotation.ConfirmItemCreateOrUpdate;

import javax.validation.constraints.Min;

@Getter
@Setter
@ConfirmItemCreateOrUpdate
public class CartItemCreateOrUpdateDto {

    private String productSku;

    @Min(value = 1)
    private Integer quantity;
}
