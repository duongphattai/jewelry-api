package springboot.jewelry.api.product.projection;

public interface ShortProductProjection extends BaseProductProjection{

    String getSlug();
    Integer getQuantity();
    String getCategorySlug();
}
