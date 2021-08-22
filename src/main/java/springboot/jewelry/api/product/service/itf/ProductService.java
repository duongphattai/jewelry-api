package springboot.jewelry.api.product.service.itf;

import org.springframework.data.domain.Pageable;
import springboot.jewelry.api.commondata.GenericService;
import springboot.jewelry.api.commondata.model.PagedResult;
import springboot.jewelry.api.product.dto.ProductCreateDto;
import springboot.jewelry.api.product.dto.ProductFilterDto;
import springboot.jewelry.api.product.model.Product;
import springboot.jewelry.api.product.projection.ProductDetailProjection;
import springboot.jewelry.api.product.projection.ProductProjection;

import java.util.List;

public interface ProductService extends GenericService<Product, Long> {

    ProductDetailProjection save(ProductCreateDto dto);

    Product updateProductInfo(ProductCreateDto dto, Long id);

    List<ProductFilterDto> findProductsByFilter(String name, String productType, Double goldType,
                                                Double minPrice, Double maxPrice);

    PagedResult<ProductDetailProjection> findProducts(Pageable pageable);
    PagedResult<ProductProjection> findProductsByNameAndSku(String searchValue, Pageable pageable);
}
