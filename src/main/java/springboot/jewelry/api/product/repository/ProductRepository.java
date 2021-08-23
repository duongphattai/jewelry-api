package springboot.jewelry.api.product.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springboot.jewelry.api.commondata.GenericRepository;
import springboot.jewelry.api.product.dto.ProductFilterDto;
import springboot.jewelry.api.product.model.Product;
import springboot.jewelry.api.product.projection.ProductSummaryProjection;


@Repository
public interface ProductRepository extends GenericRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    int countBySku(String sku);

    Page<ProductSummaryProjection> findProductDetailBy(Pageable pageable);

    @Query(value = "SELECT min(price) FROM Product")
    Double minPrice();

    @Query(value = "SELECT max(price) FROM Product ")
    Double maxPrice();
}
