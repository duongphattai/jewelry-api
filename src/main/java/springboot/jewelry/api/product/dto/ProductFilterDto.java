package springboot.jewelry.api.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ProductFilterDto {

    private String sku;

    private String name;

    private String avatar;

    private String category;

    private String supplier;

    private Double goldType;

    private Double price;

    private Integer quantity;
}
