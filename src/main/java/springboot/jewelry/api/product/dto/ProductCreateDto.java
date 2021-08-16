package springboot.jewelry.api.product.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import springboot.jewelry.api.product.validation.annotation.ExistsGoldType;
import springboot.jewelry.api.product.validation.annotation.ExistsCategoryCode;
import springboot.jewelry.api.product.validation.annotation.ExistsSupplierCode;
import springboot.jewelry.api.product.validation.annotation.UniqueSku;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;


@Getter
@Setter
public class ProductCreateDto {

    @NotBlank(message = "{product.sku.not-blank}")
    @Size(min = 3, max = 10, message = "{product.sku.size}")
    @UniqueSku
    private String sku;

    @NotBlank(message = "{product.name.not-blank}")
    @Size(min = 5, max = 100, message = "{product.name.size}")
    private String name;

    @Size(max = 500, message = "{product.description.size}")
    private String description;

    private Double goldWeight;
    private Double costPrice;
    private Double price;
    private Integer quantity;

    private MultipartFile avatar;

    @ExistsSupplierCode
    private String supplierCode;

    @ExistsCategoryCode
    private String categoryCode;

    @ExistsGoldType
    private Double goldType;

    private List<MultipartFile> images;
}
