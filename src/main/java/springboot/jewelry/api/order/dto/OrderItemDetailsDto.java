package springboot.jewelry.api.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import springboot.jewelry.api.product.dto.ShortProductDto;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class OrderItemDetailsDto {

    private ShortProductDto product;

    private Double price;

    private Integer quantity;
}
