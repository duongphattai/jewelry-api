package springboot.jewelry.api.product.converter;

import springboot.jewelry.api.product.dto.ProductDetailDto;
import springboot.jewelry.api.product.dto.ProductFilterDto;
import springboot.jewelry.api.product.dto.ProductSummaryDto;
import springboot.jewelry.api.product.model.Image;
import springboot.jewelry.api.product.model.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductConverter {
    public static List<ProductFilterDto> toProductFilterDto(List<Product> products) {
        return products.stream().map(p ->
                ProductFilterDto.builder()
                        .name(p.getName())
                        .sku(p.getSku())
                        .price(p.getPrice())
                        .category(p.getCategory().getCode())
                        .goldType(p.getGoldType().getPercentage())
                        .build()).collect(Collectors.toList());
    }

    public static List<ProductSummaryDto> toProductSummaryDto(List<Product> products) {
        return products.stream().map(p ->
                ProductSummaryDto.builder()
                        .name(p.getName())
                        .sku(p.getSku())
                        .avatar(p.getAvatar())
                        .price(p.getPrice())
                        .goldWeight(p.getGoldWeight())
                        .quantity(p.getQuantity())
                        .categoryName(p.getCategory().getName())
                        .supplierName(p.getSupplier().getName())
                        .goldTypePercentage(p.getGoldType().getPercentage())
                        .build()).collect(Collectors.toList());
    }

    public static ProductDetailDto toProductDetailDto(Product product) {
        return ProductDetailDto.builder()
                        .sku(product.getSku())
                        .avatar(product.getAvatar())
                        .price(product.getPrice())
                        .goldWeight(product.getGoldWeight())
                        .quantity(product.getQuantity())
                        .categoryName(product.getCategory().getName())
                        .supplierName(product.getSupplier().getName())
                        .goldTypePercentage(product.getGoldType().getPercentage())
                        .images(product.getImages().stream().map(Image::getGDriveId).collect(Collectors.toList()))
                        .build();
    }
}
