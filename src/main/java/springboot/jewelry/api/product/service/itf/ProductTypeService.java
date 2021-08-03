package springboot.jewelry.api.product.service.itf;

import springboot.jewelry.api.commondata.GenericService;
import springboot.jewelry.api.product.dto.ProductTypeCreateDto;
import springboot.jewelry.api.product.dto.ProductTypeUpdateDto;
import springboot.jewelry.api.product.model.ProductType;

public interface ProductTypeService extends GenericService<ProductType, Long> {
    ProductType save(ProductTypeCreateDto dto);

    ProductType updateProductTypeInfo(ProductTypeUpdateDto dto, Long id);
}
