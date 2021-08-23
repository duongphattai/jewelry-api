package springboot.jewelry.api.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ProductDetailDto {
    String sku;
    String name;
    String avatar;
    Double price;
    Double goldWeight;
    Integer quantity;

    String categoryName;
    String supplierName;
    Double goldTypePercentage;
    List<String> images;
}
