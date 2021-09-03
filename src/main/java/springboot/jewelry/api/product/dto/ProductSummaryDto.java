package springboot.jewelry.api.product.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ProductSummaryDto {

    Long id;
    String sku;
    String name;
    String avatar;
    Double price;
    Double goldWeight;
    Integer quantity;

    String categoryName;
    String supplierName;
    Double goldTypePercentage;
}
