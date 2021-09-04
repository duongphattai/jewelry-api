package springboot.jewelry.api.order.repository;

import org.springframework.stereotype.Repository;
import springboot.jewelry.api.commondata.GenericRepository;
import springboot.jewelry.api.order.model.Order;

@Repository
public interface OrderRepository extends GenericRepository<Order, Long> {
}
