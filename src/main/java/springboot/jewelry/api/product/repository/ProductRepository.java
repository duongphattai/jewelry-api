package springboot.jewelry.api.product.repository;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springboot.jewelry.api.product.model.Product;
import springboot.jewelry.api.product.projection.ProductProjection;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT p FROM Product p")
    List<Product> findAllProductWithPage(Pageable pageable);

    @Query(value = "SELECT p.name AS name, p.description AS description, p.price AS price, p.supplier.name AS supplierName FROM Product p")
    List<ProductProjection> findListProduct(Pageable pageable);

    int countBySku(String sku);
}
