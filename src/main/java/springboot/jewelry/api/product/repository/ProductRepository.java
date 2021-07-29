package springboot.jewelry.api.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.jewelry.api.product.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
