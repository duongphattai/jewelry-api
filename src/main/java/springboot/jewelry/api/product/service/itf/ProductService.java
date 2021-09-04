package springboot.jewelry.api.product.service.itf;

import org.springframework.data.domain.Pageable;
import springboot.jewelry.api.commondata.GenericService;
import springboot.jewelry.api.commondata.model.PagedResult;
import springboot.jewelry.api.commondata.model.SearchCriteria;
import springboot.jewelry.api.product.dto.*;
import springboot.jewelry.api.product.dto.ProductCreateDto;
import springboot.jewelry.api.product.dto.ProductSummaryDto;
import springboot.jewelry.api.product.model.Product;

import springboot.jewelry.api.product.projection.ProductDetailsAdminProjection;
import springboot.jewelry.api.product.projection.ProductSummaryProjection;
import springboot.jewelry.api.product.projection.ShortProductProjection;

import java.util.Optional;

public interface ProductService extends GenericService<Product, Long> {

    ProductDetailsDto save(ProductCreateDto dto);

    Product updateProductInfo(ProductUpdateDto dto, Long id);

    PagedResult<ProductSummaryProjection> findProductsSummary(Pageable pageable);

    PagedResult<ProductSummaryDto> findProductsSummaryWithSearch(SearchCriteria searchCriteria, Pageable pageable);

    PagedResult<ShortProductDto> findShortProductsWithSearch(SearchCriteria searchCriteria, Pageable pageable);

    PagedResult<ShortProductDto> findShortProducts(Pageable pageable);

    PagedResult<ShortProductDto> findShortProductsByCategory(String categorySlug, Pageable pageable);

    ProductDetailsDto findProductDetails(String slug);

    ProductDetailsAdminDto findProductById(Long id);
}
