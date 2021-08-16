package springboot.jewelry.api.product.converter;

import springboot.jewelry.api.product.dto.ProductFilterDto;
import springboot.jewelry.api.product.model.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductConverter {
    public static List<ProductFilterDto> convertToProductFilterDto(List<Product> products) {
        return products.stream().map(p ->
                ProductFilterDto.builder()
                        .name(p.getName())
                        .sku(p.getSku())
                        .price(p.getPrice())
                        .productType(p.getProductType().getCode())
                        .goldType(p.getGoldType().getPercentage())
                        .build()).collect(Collectors.toList());
    }
}
