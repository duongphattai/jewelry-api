package springboot.jewelry.api.product.dto;

import lombok.Getter;
import lombok.Setter;
import springboot.jewelry.api.product.validation.annotation.ExistsGoldType;
import springboot.jewelry.api.product.validation.annotation.ExistsProductTypeCode;
import springboot.jewelry.api.product.validation.annotation.ExistsSupplierCode;
import springboot.jewelry.api.product.validation.annotation.UniqueSku;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@Setter
public class ProductCreateDto {

    @NotBlank(message = "{Product.sku.NotBlank}")
    @Size(min = 5, max = 5, message = "{Product.sku.Size}")
    @UniqueSku
    private String sku;

    @NotBlank(message = "{Product.name.NotBlank}")
    @Size(min = 5, max = 100, message = "{Product.name.Size}")
    private String name;

    @Size(max = 500, message = "{Product.description.Size}")
    private String description;

    private Double goldWeight;

    private Double costPrice;
    private Double price;
    private Integer quantity;

    @ExistsSupplierCode
    private String supplierCode;

    @ExistsProductTypeCode
    private String productTypeCode;

    @ExistsGoldType
    private Double goldType;

}
