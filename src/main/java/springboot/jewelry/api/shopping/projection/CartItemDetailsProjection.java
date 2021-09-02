package springboot.jewelry.api.shopping.projection;

public interface CartItemDetailsProjection {

    String  getProductSku();
    String  getProductName();
    String  getProductAvatar();
    Double  getProductPrice();
    Integer getProductQuantity();
    String  getProductSlug();
    String  getProductCategorySlug();

    Integer getQuantity();
}
