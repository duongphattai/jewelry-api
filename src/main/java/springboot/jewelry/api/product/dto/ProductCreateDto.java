package springboot.jewelry.api.product.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
public class ProductCreateDto {

    private String sku;
    private String name;
    private String description;

    private Double goldWeight;

    private Double costPrice;
    private Double price;
    private Integer quantity;

    private String codeSupplier;
    private String codeProductType;
    private Double goldType;

}
