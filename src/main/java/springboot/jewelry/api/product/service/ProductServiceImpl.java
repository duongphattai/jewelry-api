package springboot.jewelry.api.product.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import springboot.jewelry.api.commondata.GenericServiceImpl;
import springboot.jewelry.api.commondata.model.PagedResult;
import springboot.jewelry.api.product.dto.ProductCreateDto;
import springboot.jewelry.api.product.model.GoldType;
import springboot.jewelry.api.product.model.Product;
import springboot.jewelry.api.product.model.ProductType;
import springboot.jewelry.api.product.projection.ProductProjection;
import springboot.jewelry.api.product.repository.GoldTypeRepository;
import springboot.jewelry.api.product.repository.ProductRepository;
import springboot.jewelry.api.product.repository.ProductTypeRepository;
import springboot.jewelry.api.product.service.itf.ProductService;
import springboot.jewelry.api.supplier.model.Supplier;
import springboot.jewelry.api.supplier.repository.SupplierRepository;
import springboot.jewelry.api.util.MapDtoToModel;

import java.util.*;

@AllArgsConstructor
@Service
public class ProductServiceImpl extends GenericServiceImpl<Product, Long> implements ProductService {

    private ProductRepository productRepository;
    private SupplierRepository supplierRepository;
    private ProductTypeRepository productTypeRepository;
    private GoldTypeRepository goldTypeRepository;
    private MapDtoToModel<Object, Product> mapper;

    @Override
    public Product save(ProductCreateDto dto) {
        Product newProduct = new Product();
        newProduct = mapper.map(dto, newProduct);
        newProduct.setTotalCostPrice(dto.getCostPrice() * dto.getQuantity());

        Optional<Supplier> supplierOpt = supplierRepository.findByCode(dto.getSupplierCode());
        if(supplierOpt.isPresent()){
            newProduct.setSupplier(supplierOpt.get());
        }

        Optional<ProductType> productTypeOpt = productTypeRepository.findByCode(dto.getProductTypeCode());
        if(productTypeOpt.isPresent()){
            newProduct.setProductType(productTypeOpt.get());
        }

        Optional<GoldType> goldTypeOpt = goldTypeRepository.findByPercentage(dto.getGoldType());
        if(goldTypeOpt.isPresent()){
            newProduct.setGoldType(goldTypeOpt.get());
        }

        return productRepository.save(newProduct);
    }

    @Override
    public Product updateProductInfo(ProductCreateDto dto, Long id) {
        Product productUpdate = productRepository.getOne(id);
        productUpdate = mapper.map(dto, productUpdate);
        return productRepository.save(productUpdate);
    }

    @Override
    public List<ProductProjection> findListProduct(int pageIndex, String sortBy) {
        Pageable pageable = PageRequest.of(pageIndex,9, Sort.by(Sort.Direction.ASC, sortBy));
        return productRepository.findListProduct(pageable);
    }

    @Override
    public PagedResult<ProductProjection> findProducts(Pageable pageable) {
        Page<ProductProjection> result = productRepository.findProducts(pageable);
        return new PagedResult<>(
                result.getContent(),
                result.getTotalElements(),
                result.getTotalPages(),
                result.getNumber() + 1
        );
    }

}
