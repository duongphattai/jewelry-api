package springboot.jewelry.api.product.projection;



import org.springframework.beans.factory.annotation.Value;
import springboot.jewelry.api.product.model.GoldType;
import springboot.jewelry.api.product.model.Image;

import java.util.List;

public interface ProductSummaryProjection extends ProductProjection{

    Double getGoldWeight();
    Integer getQuantity();

    //@Value("#{target.supplier.name}")
    String getSupplierName();

    //@Value("#{target.category.name}")
    String getCategoryName();

    //@Value("#{target.goldType.percentage}")
    Double getGoldTypePercentage();

}
