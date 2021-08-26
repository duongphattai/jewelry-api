package springboot.jewelry.api.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ShortProductDto {
    String sku;
    String name;
    String avatar;
    Double price;
    String slug;
    Boolean inStock;
    String categorySlug;
}
