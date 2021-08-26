package springboot.jewelry.api.product.projection;

public interface ProductDetailsProjection extends BaseProductProjection {

    String getDescription();
    Double getGoldWeight();
    Integer getQuantity();
    String getCategoryName();
    Double getGoldTypePercentage();
}
