package springboot.jewelry.api.product.projection;

import springboot.jewelry.api.product.model.Product;

public interface ProductProjection {

    String getSku();
    String getName();
    Double getPrice();

}
