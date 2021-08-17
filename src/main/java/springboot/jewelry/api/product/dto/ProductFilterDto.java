package springboot.jewelry.api.product.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductFilterDto {
    private String name;

    private String sku;

    private Double price;

    private String category;

    private Double goldType;
}
