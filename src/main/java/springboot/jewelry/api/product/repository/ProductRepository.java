package springboot.jewelry.api.product.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import springboot.jewelry.api.commondata.GenericRepository;
import springboot.jewelry.api.product.model.Product;
import springboot.jewelry.api.product.projection.ProductDetailsAdminProjection;
import springboot.jewelry.api.product.projection.ProductDetailsProjection;
import springboot.jewelry.api.product.projection.ProductSummaryProjection;
import springboot.jewelry.api.product.projection.ShortProductProjection;

import java.util.Optional;


@Repository
public interface ProductRepository extends GenericRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    int countBySku(String sku);

    Optional<Product> findBySku(String sku);

    Page<ProductSummaryProjection> findProductsSummaryBy(Pageable pageable);

    Page<ShortProductProjection> findShortProductsBy(Pageable pageable);

    Page<ShortProductProjection> findShortProductsByCategorySlug(String categorySlug, Pageable pageable);

    Optional<ProductDetailsProjection> findProductDetailsBySlug(@Param("slug") String slug);

    @Query("SELECT p.id AS id, " +
                    "p.name AS name, " +
                    "p.sku AS sku, " +
                    "p.description AS description, " +
                    "p.costPrice AS costPrice, " +
                    "p.price AS price, " +
                    "p.goldWeight AS goldWeight, " +
                    "p.quantity AS quantity, " +
                    "p.supplier.name AS supplierName, " +
                    "p.category.name AS categoryName, " +
                    "p.goldType.percentage AS goldTypePercentage," +
                    "p.avatar AS avatar " +
                    "FROM Product p " +
                    "WHERE p.id=:id")
    Optional<ProductDetailsAdminProjection> findProductDetailsAdminById(@Param("id") Long id);

}
