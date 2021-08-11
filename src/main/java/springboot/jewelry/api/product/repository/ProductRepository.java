package springboot.jewelry.api.product.repository;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springboot.jewelry.api.product.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> , JpaSpecificationExecutor<Product> {

    @Query(value = "SELECT p FROM Product p")
    List<Product> findAllProductWithPage(Pageable pageable);

    int countBySku(String sku);

    @Query(value = "SELECT min(price) FROM Product")
    Double minPrice();

    @Query(value = "SELECT max(price) FROM Product ")
    Double maxPrice();

}
