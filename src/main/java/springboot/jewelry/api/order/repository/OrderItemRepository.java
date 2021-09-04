package springboot.jewelry.api.order.repository;

import org.springframework.stereotype.Repository;
import springboot.jewelry.api.commondata.GenericRepository;
import springboot.jewelry.api.order.model.OrderItem;

@Repository
public interface OrderItemRepository extends GenericRepository<OrderItem, Long> {
}
