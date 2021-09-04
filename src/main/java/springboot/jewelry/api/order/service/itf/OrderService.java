package springboot.jewelry.api.order.service.itf;

import springboot.jewelry.api.commondata.GenericService;
import springboot.jewelry.api.order.dto.OrderCreateDto;
import springboot.jewelry.api.order.dto.OrderDetailsDto;
import springboot.jewelry.api.order.model.Order;
import springboot.jewelry.api.security.dto.CustomerPrincipalDto;

public interface OrderService extends GenericService<Order, Long> {

    OrderDetailsDto save(OrderCreateDto dto, CustomerPrincipalDto curUserDto);
}
