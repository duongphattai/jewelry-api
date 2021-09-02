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
    private String sku;
    private String name;
    private String avatar;
    private Double price;
    private String slug;
    private Boolean inStock;
    private String categorySlug;
}
