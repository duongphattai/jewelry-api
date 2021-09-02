package springboot.jewelry.api.shopping.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import springboot.jewelry.api.product.dto.ShortProductDto;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CartItemDetailsDto {

    private ShortProductDto product;
    private Integer quantity;
}
