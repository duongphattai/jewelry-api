package springboot.jewelry.api.shopping.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CartDetailsDto {

    private Double total;
    private List<CartItemDetailsDto> items;
}
