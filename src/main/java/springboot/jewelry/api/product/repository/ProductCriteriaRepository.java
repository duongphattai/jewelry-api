package springboot.jewelry.api.product.repository;

import org.springframework.data.domain.Page;
import springboot.jewelry.api.product.dto.ProductFilterDto;
import springboot.jewelry.api.product.dto.ProductPageDto;
import springboot.jewelry.api.product.dto.ProductFilterCriteriaDto;

public interface ProductCriteriaRepository {
    Page<ProductFilterDto> findProductsByFilters(ProductPageDto productPageDto,
                                                 ProductFilterCriteriaDto productFilterCriteriaDto);
}
