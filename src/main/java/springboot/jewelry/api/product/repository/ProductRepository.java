package springboot.jewelry.api.product.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springboot.jewelry.api.product.model.Product;
import springboot.jewelry.api.product.projection.ProductProjection;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> , JpaSpecificationExecutor<Product> {

    int countBySku(String sku);

    @Query(value =
            "SELECT p.sku AS sku, " +
                    "p.name AS name, " +
                    "p.price AS price " +
            "FROM Product p")
    Page<ProductProjection> findProducts(Pageable pageable);

    @Query(value = "SELECT min(price) FROM Product")
    Double minPrice();

    @Query(value = "SELECT max(price) FROM Product ")
    Double maxPrice();

}
