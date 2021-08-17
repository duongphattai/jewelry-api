package springboot.jewelry.api.product.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductFilterCriteriaDto {

    private String name;

    private String sku;

    private Double minPrice;

    private Double maxPrice;

    private String category;

    private Double goldType;
}
