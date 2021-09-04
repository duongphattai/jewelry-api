package springboot.jewelry.api.product.projection;


public interface BaseProductProjection {

    Long getId();
    String getSku();
    String getName();
    Double getPrice();
    String getAvatar();

}
