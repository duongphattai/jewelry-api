package springboot.jewelry.api.product.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import springboot.jewelry.api.commondata.GenericServiceImpl;
import springboot.jewelry.api.product.dto.ProductTypeCreateDto;
import springboot.jewelry.api.product.dto.ProductTypeUpdateDto;
import springboot.jewelry.api.product.model.ProductType;
import springboot.jewelry.api.product.repository.ProductTypeRepository;
import springboot.jewelry.api.product.service.itf.ProductTypeService;
import springboot.jewelry.api.util.MapDtoToModel;

@AllArgsConstructor
@Service
public class ProductTypeServiceImpl extends GenericServiceImpl<ProductType, Long> implements ProductTypeService {

    private final ProductTypeRepository productTypeRepository;
    private final MapDtoToModel<Object, ProductType> mapper;

    @Override
    public ProductType save(ProductTypeCreateDto dto) {
        ProductType newProductType = new ProductType();
        newProductType = mapper.map(dto, newProductType);
        return productTypeRepository.save(newProductType);
    }

    @Override
    public ProductType updateProductTypeInfo(ProductTypeUpdateDto dto, Long id) {
        ProductType productTypeUpdate = productTypeRepository.getOne(id);
        productTypeUpdate = mapper.map(dto, productTypeUpdate);
        return productTypeRepository.save(productTypeUpdate);
    }
}
