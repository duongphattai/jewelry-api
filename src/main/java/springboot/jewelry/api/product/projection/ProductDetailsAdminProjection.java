package springboot.jewelry.api.product.projection;

import java.util.Set;

public interface ProductDetailsAdminProjection extends ProductDetailsProjection{

    Double getCostPrice();
    String getSupplierName();
    Set<String> getImagesGDriveId();
}
