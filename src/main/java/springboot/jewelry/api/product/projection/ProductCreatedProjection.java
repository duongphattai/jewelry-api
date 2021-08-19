package springboot.jewelry.api.product.projection;

import springboot.jewelry.api.supplier.model.Supplier;

import java.util.List;

public interface ProductCreatedProjection {

    String getSku();
    String getName();
    String getDescription();
    Double getGoldWeight();
    Double getCostPrice();
    Double getPrice();
    Integer getQuantity();
    String getAvatar();

    Supplier getSupplier();
    interface Supplier {
        String getCode();
        String getName();
    }

    Category getCategory();
    interface Category {
        String getCode();
        String getName();
    }

    GoldType getGoldType();
    interface GoldType {
        Double getPercentage();
    }

    List<Image> getImages();
    interface Image {
        String getGDriveId();
    }
}
