package springboot.jewelry.api.product.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;


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

}
