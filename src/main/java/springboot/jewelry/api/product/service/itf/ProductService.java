package springboot.jewelry.api.product.service.itf;

import springboot.jewelry.api.commondata.GenericService;
import springboot.jewelry.api.product.dto.ProductCreateDto;
import springboot.jewelry.api.product.model.Product;

import java.util.List;

public interface ProductService extends GenericService<Product, Long> {
    Product save(ProductCreateDto dto);

    Product updateProductInfo(ProductCreateDto dto, Long id);

    List<Product> findAllProductWithPage(int pageIndex, String sortBy);
}
