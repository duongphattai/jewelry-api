package springboot.jewelry.api.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ProductDetailsDto {
    String sku;
    String name;
    String avatar;
    String description;
    Double price;
    Double goldWeight;
    Boolean inStock;

    String categoryName;
    Double goldTypePercentage;
    Set<String> images;
}
