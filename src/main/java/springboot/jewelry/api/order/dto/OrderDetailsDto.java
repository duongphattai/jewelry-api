package springboot.jewelry.api.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import springboot.jewelry.api.order.util.OrderStatus;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class OrderDetailsDto {

    private Long id;

    private Double total;

    private OrderStatus status;

    private String fullName;

    private String address;

    private String phoneNumber;

    private String content;

    List<OrderItemDetailsDto> items;
}
