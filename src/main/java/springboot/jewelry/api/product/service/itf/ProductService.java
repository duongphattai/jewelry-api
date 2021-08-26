package springboot.jewelry.api.product.service.itf;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import springboot.jewelry.api.commondata.GenericService;
import springboot.jewelry.api.commondata.model.PagedResult;
import springboot.jewelry.api.commondata.model.SearchCriteria;
import springboot.jewelry.api.product.dto.ProductCreateDto;
import springboot.jewelry.api.product.dto.ProductDetailDto;
import springboot.jewelry.api.product.dto.ProductFilterDto;
import springboot.jewelry.api.product.dto.ProductSummaryDto;
import springboot.jewelry.api.product.model.Product;



import springboot.jewelry.api.product.projection.ProductSummaryProjection;

import springboot.jewelry.api.product.projection.ProductProjection;

public interface ProductService extends GenericService<Product, Long> {

    ProductDetailDto save(ProductCreateDto dto);

    Product updateProductInfo(ProductCreateDto dto, Long id);

    PagedResult<ProductSummaryProjection> findProductsSummary(Pageable pageable);
    PagedResult<ProductSummaryDto> findProductsSummaryWithSearch(SearchCriteria searchCriteria, Pageable pageable);

}
