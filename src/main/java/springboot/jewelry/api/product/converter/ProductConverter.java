package springboot.jewelry.api.product.converter;

import springboot.jewelry.api.product.dto.ProductDetailsDto;
import springboot.jewelry.api.product.dto.ProductFilterDto;
import springboot.jewelry.api.product.dto.ProductSummaryDto;
import springboot.jewelry.api.product.dto.ShortProductDto;
import springboot.jewelry.api.product.model.Image;
import springboot.jewelry.api.product.model.Product;
import springboot.jewelry.api.product.projection.ProductDetailsProjection;
import springboot.jewelry.api.product.projection.ProductSummaryProjection;
import springboot.jewelry.api.product.projection.ShortProductProjection;

import java.util.List;
import java.util.Set;
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

    public static List<ProductSummaryDto> entityToProductSummaryDto(List<Product> products) {
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

    public static List<ShortProductDto> projectionToShortProductDto(List<ShortProductProjection> projections) {
        return projections.stream().map(p ->
                ShortProductDto.builder()
                        .name(p.getName())
                        .sku(p.getSku())
                        .avatar(p.getAvatar())
                        .price(p.getPrice())
                        .slug(p.getSlug())
                        .inStock(p.getQuantity() > 0)
                        .categorySlug(p.getCategorySlug())
                        .build()).collect(Collectors.toList());
    }

    public static ProductDetailsDto entityToProductDetailDto(Product entity) {
        return ProductDetailsDto.builder()
                        .sku(entity.getSku())
                        .avatar(entity.getAvatar())
                        .price(entity.getPrice())
                        .goldWeight(entity.getGoldWeight())
                        .inStock(entity.getQuantity() > 0)
                        .categoryName(entity.getCategory().getName())
                        .goldTypePercentage(entity.getGoldType().getPercentage())
                        .images(entity.getImages().stream().map(Image::getGDriveId).collect(Collectors.toSet()))
                        .build();
    }

    public static ProductDetailsDto projectionToProductDetailDto(ProductDetailsProjection projection, Set<String> images) {
        return ProductDetailsDto.builder()
                .sku(projection.getSku())
                .name(projection.getName())
                .avatar(projection.getAvatar())
                .description(projection.getDescription())
                .price(projection.getPrice())
                .goldWeight(projection.getGoldWeight())
                .inStock(projection.getQuantity() > 0)
                .categoryName(projection.getCategoryName())
                .goldTypePercentage(projection.getGoldTypePercentage())
                .images(images)
                .build();
    }
}
