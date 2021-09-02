package springboot.jewelry.api.product.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import springboot.jewelry.api.commondata.GenericRepository;
import springboot.jewelry.api.product.model.Product;
import springboot.jewelry.api.product.projection.ProductDetailsProjection;
import springboot.jewelry.api.product.projection.ProductSummaryProjection;
import springboot.jewelry.api.product.projection.ShortProductProjection;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends GenericRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    int countBySku(String sku);

    Optional<Product> findBySku(String sku);

    Page<ProductSummaryProjection> findProductsSummaryBy(Pageable pageable);

    Page<ShortProductProjection> findShortProductsBy(Pageable pageable);

    Page<ShortProductProjection> findShortProductsByCategorySlug(String categorySlug, Pageable pageable);

    Optional<ProductDetailsProjection> findProductDetailsBySlug(@Param("slug") String slug);

}
