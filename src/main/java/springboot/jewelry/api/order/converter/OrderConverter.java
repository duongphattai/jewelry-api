package springboot.jewelry.api.order.converter;

import springboot.jewelry.api.order.dto.OrderDetailsDto;
import springboot.jewelry.api.order.dto.OrderItemDetailsDto;
import springboot.jewelry.api.order.model.Order;
import springboot.jewelry.api.order.model.OrderItem;
import springboot.jewelry.api.product.dto.ShortProductDto;
import springboot.jewelry.api.product.model.Product;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OrderConverter {

    public static OrderDetailsDto toOrderDetailsDto(Order order) {
        List<OrderItemDetailsDto> items = order.getItems().stream()
                .sorted(Comparator.comparing(OrderItem::getCreatedAt))
                .map(item -> {
                    Product product = item.getProduct();
                    return OrderItemDetailsDto.builder()
                            .product(ShortProductDto.builder()
                                    .sku(product.getSku())
                                    .name(product.getName())
                                    .avatar(product.getAvatar())
                                    .price(product.getPrice())
                                    .slug(product.getSlug())
                                    .categorySlug(product.getCategory().getSlug())
                                    .build())
                            .price(product.getPrice())
                            .quantity(item.getQuantity())
                            .build();
                })
                .collect(Collectors.toList());

        return OrderDetailsDto.builder()
                .id(order.getId())
                .total(order.getTotal())
                .status(order.getStatus())
                .fullName(order.getFullName())
                .address(order.getAddress())
                .phoneNumber(order.getPhoneNumber())
                .content(order.getContent())
                .items(items)
                .build();
    }
}
