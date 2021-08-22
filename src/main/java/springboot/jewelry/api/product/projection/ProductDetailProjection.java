package springboot.jewelry.api.product.projection;



import org.springframework.beans.factory.annotation.Value;
import springboot.jewelry.api.product.model.Image;

import java.util.List;

public interface ProductDetailProjection extends ProductProjection{

    Double getGoldWeight();
    Integer getQuantity();

    //@Value("#{target.supplier.code}")
    String getSupplierCode();
    //@Value("#{target.supplier.name}")
    String getSupplierName();

    //@Value("#{target.category.code}")
    String getCategoryCode();
    //@Value("#{target.category.name}")
    String getCategoryName();

    //@Value("#{target.goldType.percentage}")
    Double getGoldTypePercentage();

    //@Value("#{target.images.gDriveId}")
    //List<Image> getImages();
}
