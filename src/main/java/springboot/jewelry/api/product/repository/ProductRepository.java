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

import java.util.Optional;


@Repository
public interface ProductRepository extends GenericRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    int countBySku(String sku);

    Page<ProductSummaryProjection> findProductsSummaryBy(Pageable pageable);

    Page<ShortProductProjection> findShortProductsBy(Pageable pageable);

//    @Query(value = "SELECT p.sku as sku, p.name as name, p.avatar as avatar, p.price as price, p.description as description, " +
//            "p.goldWeight as goldWeight, p.quantity as quantity, c.name as categoryName, g.percentage as goldTypePercentage, " +
//            "i.gDriveId as imagesGDriveId " +
//                "FROM " +
//                "   Product p " +
//                "JOIN " +
//                "   Image i ON p.id = i.product.id " +
//                "JOIN FETCH " +
//                "   Category c ON p.category.id = c.id " +
//                "JOIN FETCH " +
//                "   GoldType g ON p.goldType.percentage = g.percentage " +
//                "WHERE p.slug = :slug")
    Optional<ProductDetailsProjection> findProductDetailsBySlug(@Param("slug") String slug);

    @Query(value = "SELECT min(price) FROM Product")
    Double minPrice();

    @Query(value = "SELECT max(price) FROM Product ")
    Double maxPrice();
}
