package springboot.jewelry.api.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ProductDetailsAdminDto {

    Long id;
    String sku;
    String name;
    String avatar;
    String description;
    Double costPrice;
    Double price;
    Double goldWeight;
    Integer quantity;
    String supplierCode;
    String categoryCode;
    Double goldTypePercentage;
    Set<String> images;
}
