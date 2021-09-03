package springboot.jewelry.api.product.projection;


public interface ProductSummaryProjection extends BaseProductProjection {


    Double getGoldWeight();
    Integer getQuantity();

    //@Value("#{target.supplier.name}")
    String getSupplierName();

    //@Value("#{target.category.name}")
    String getCategoryName();

    //@Value("#{target.goldType.percentage}")
    Double getGoldTypePercentage();

}
