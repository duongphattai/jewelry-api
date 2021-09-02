package springboot.jewelry.api.product.repository;

import org.springframework.data.jpa.repository.Query;
import springboot.jewelry.api.commondata.GenericRepository;
import springboot.jewelry.api.product.model.Image;

import java.util.Set;

public interface ImageRepository extends GenericRepository<Image, Long> {

//    @Query(value = "SELECT i.gDriveId " +
//                    "FROM " +
//                    "   Image i " +
//                    "LEFT JOIN " +
//                    "   Product p " +
//                    "ON i.product.id = p.id " +
//                    "WHERE p.sku = ?1")
    @Query(value = "SELECT i.gDriveId FROM Image i WHERE i.product.sku = ?1")
    Set<String> findGDriveIdByProductSku(String productSku);
}