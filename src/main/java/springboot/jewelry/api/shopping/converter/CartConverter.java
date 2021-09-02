package springboot.jewelry.api.shopping.converter;

import springboot.jewelry.api.product.dto.ShortProductDto;
import springboot.jewelry.api.product.model.Product;
import springboot.jewelry.api.shopping.dto.CartDetailsDto;
import springboot.jewelry.api.shopping.dto.CartItemDetailsDto;
import springboot.jewelry.api.shopping.model.Cart;
import springboot.jewelry.api.shopping.model.CartItem;
import springboot.jewelry.api.shopping.projection.CartItemWithQuantityProjection;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CartConverter {

    public static CartDetailsDto toCartDetailsDto(Cart cart) {
        List<CartItemDetailsDto> items = cart.getItems().stream()
                .sorted(Comparator.comparing(CartItem :: getCreatedAt))
                .map(item -> {
                    Product product = item.getProduct();
                    return CartItemDetailsDto.builder()
                            .product(ShortProductDto.builder()
                                    .sku(product.getSku())
                                    .name(product.getName())
                                    .avatar(product.getAvatar())
                                    .price(product.getPrice())
                                    .slug(product.getSlug())
                                    .categorySlug(product.getCategory().getSlug())
                                    .build())
                            .quantity(item.getQuantity())
                            .build();
                })
                .collect(Collectors.toList());

        return CartDetailsDto.builder()
                .total(cart.getTotal())
                .items(items)
                .build();
    }

    public static CartDetailsDto toCartDetailsDto(Double total, List<CartItemWithQuantityProjection> itemProjections) {
        List<CartItemDetailsDto> items = itemProjections.stream()
                .sorted(Comparator.comparing(CartItemWithQuantityProjection :: getCreatedAt))
                .map(item -> CartItemDetailsDto.builder()
                        .product(ShortProductDto.builder()
                                .sku(item.getProductSku())
                                .name(item.getProductName())
                                .avatar(item.getProductAvatar())
                                .price(item.getProductPrice())
                                .slug(item.getProductSlug())
                                .categorySlug(item.getProductCategorySlug())
                                .build())
                        .quantity(item.getQuantity())
                        .build())
                .collect(Collectors.toList());

        return CartDetailsDto.builder()
                .total(total)
                .items(items)
                .build();
    }
}
