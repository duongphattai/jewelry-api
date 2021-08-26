package springboot.jewelry.api.product.service.itf;

import org.springframework.data.domain.Pageable;
import springboot.jewelry.api.commondata.GenericService;
import springboot.jewelry.api.commondata.model.PagedResult;
import springboot.jewelry.api.commondata.model.SearchCriteria;
import springboot.jewelry.api.product.dto.*;
import springboot.jewelry.api.product.model.Product;
import springboot.jewelry.api.product.projection.ProductSummaryProjection;
import springboot.jewelry.api.product.projection.ShortProductProjection;

import java.util.List;

public interface ProductService extends GenericService<Product, Long> {

    ProductDetailsDto save(ProductCreateDto dto);

    Product updateProductInfo(ProductCreateDto dto, Long id);

    List<ProductFilterDto> findProductsByFilter(String name, String productType, Double goldType,
                                                Double minPrice, Double maxPrice);

    PagedResult<ProductSummaryProjection> findProductsSummary(Pageable pageable);
    PagedResult<ProductSummaryDto> findProductsSummaryWithSearch(SearchCriteria searchCriteria, Pageable pageable);

    PagedResult<ShortProductDto> findShortProducts(Pageable pageable);

    ProductDetailsDto findProductDetails(String slug);
}
