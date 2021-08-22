package springboot.jewelry.api.product.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springboot.jewelry.api.commondata.GenericRepository;
import springboot.jewelry.api.product.model.Product;
import springboot.jewelry.api.product.projection.ProductDetailProjection;



@Repository
public interface ProductRepository extends GenericRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    int countBySku(String sku);

    Page<ProductDetailProjection> findProductDetailBy(Pageable pageable);

    @Query(value = "SELECT p.sku AS sku, " +
                           "p.name AS name, " +
                           "p.price AS price, " +
                           "p.avatar AS avatar, " +
                           "p.goldWeight AS goldWeight, " +
                           "p.quantity AS quantity, " +
                           "s.code AS supplierCode, " +
                           "s.name AS supplierName, " +
                           "c.code AS categoryCode, " +
                           "c.name AS categoryName, " +
                           "g.percentage AS goldTypePecentage, " +
                           "i.gDriveId AS ImagesGDriveId " +
                    "FROM Product p JOIN p.supplier s " +
                        "JOIN p.category c " +
                        "JOIN p.goldType g JOIN p.images i")
    Page<ProductDetailProjection> findProductDetail1By(Pageable pageable);

    @Query(value = "SELECT min(price) FROM Product")
    Double minPrice();

    @Query(value = "SELECT max(price) FROM Product ")
    Double maxPrice();
}
