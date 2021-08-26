package springboot.jewelry.api.product.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class ProductPageDto {
    private int pageNumber = 0;
    private int pageSize = 9;
    private Sort.Direction sortDirection = Sort.Direction.ASC;
    private String sortBy = "sku";
}
