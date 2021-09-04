package springboot.jewelry.api.order.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.jewelry.api.commondata.GenericServiceImpl;
import springboot.jewelry.api.customer.repository.CustomerRepository;
import springboot.jewelry.api.order.converter.OrderConverter;
import springboot.jewelry.api.order.dto.OrderCreateDto;
import springboot.jewelry.api.order.dto.OrderDetailsDto;
import springboot.jewelry.api.order.model.Order;
import springboot.jewelry.api.order.model.OrderItem;
import springboot.jewelry.api.order.repository.OrderItemRepository;
import springboot.jewelry.api.order.repository.OrderRepository;
import springboot.jewelry.api.order.service.itf.OrderService;
import springboot.jewelry.api.order.util.OrderStatus;
import springboot.jewelry.api.product.model.Product;
import springboot.jewelry.api.security.dto.CustomerPrincipalDto;
import springboot.jewelry.api.shopping.model.Cart;
import springboot.jewelry.api.shopping.model.CartItem;
import springboot.jewelry.api.shopping.repository.CartItemRepository;
import springboot.jewelry.api.shopping.repository.CartRepository;
import springboot.jewelry.api.util.MapDtoToModel;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Service
public class OrderServiceImpl extends GenericServiceImpl<Order, Long> implements OrderService {

    private OrderRepository orderRepository;
    private CartRepository cartRepository;

    private MapDtoToModel<Object, Order> mapper;

    @Override
    @Transactional
    public OrderDetailsDto save(OrderCreateDto dto, CustomerPrincipalDto curUserDto) {
        Cart cart = cartRepository.findCartByCustomerEmail(curUserDto.getEmail());
        Order newOrder = new Order();

        newOrder = mapper.map(dto, newOrder);
        newOrder.setCustomer(cart.getCustomer());
        newOrder.setTotal(cart.getTotal());
        newOrder.setStatus(OrderStatus.NEW);

        Set<CartItem> items = new HashSet<>(cart.getItems());
        for(CartItem item : items) {
            Product product = item.getProduct();
            newOrder.addItem(new OrderItem(product, product.getPrice(), item.getQuantity()));
            product.setQuantity(product.getQuantity() - item.getQuantity());
            cart.removeItem(item);
        }
        cart.setTotal(0.0);
        cartRepository.save(cart);
        return OrderConverter.toOrderDetailsDto(orderRepository.save(newOrder));
    }
}
