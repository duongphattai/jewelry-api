package springboot.jewelry.api.product.service;

import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import springboot.jewelry.api.commondata.GenericServiceImpl;
import springboot.jewelry.api.commondata.model.PagedResult;

import org.springframework.data.domain.Pageable;

import springboot.jewelry.api.gdrive.manager.itf.GDriveFileManager;
import springboot.jewelry.api.gdrive.manager.itf.GDriveFolderManager;
import springboot.jewelry.api.product.dto.ProductCreateDto;
import springboot.jewelry.api.product.dto.ProductFilterCriteriaDto;
import springboot.jewelry.api.product.dto.ProductFilterDto;
import springboot.jewelry.api.product.dto.ProductPageDto;
import springboot.jewelry.api.product.model.GoldType;
import springboot.jewelry.api.product.model.Image;
import springboot.jewelry.api.product.model.Product;
import springboot.jewelry.api.product.model.Category;

import springboot.jewelry.api.product.projection.ProductProjection;
import springboot.jewelry.api.product.repository.*;
import springboot.jewelry.api.product.service.itf.ProductService;
import springboot.jewelry.api.supplier.model.Supplier;
import springboot.jewelry.api.supplier.repository.SupplierRepository;
import springboot.jewelry.api.util.MapDtoToModel;

import java.util.*;

@AllArgsConstructor
@Service
public class ProductServiceImpl extends GenericServiceImpl<Product, Long> implements ProductService {

    private ProductCriteriaRepository productCriteriaRepository;
    private ProductRepository productRepository;
    private SupplierRepository supplierRepository;
    private CategoryRepository categoryRepository;
    private GoldTypeRepository goldTypeRepository;

    private GDriveFolderManager gDriveFolderManager;
    private GDriveFileManager gDriveFileManager;
    private Environment env;
    private MapDtoToModel<Object, Product> mapper;

    @Override
    public Product save(ProductCreateDto dto) {
        Product newProduct = new Product();
        newProduct = mapper.map(dto, newProduct);
        newProduct.setTotalCostPrice(dto.getCostPrice() * dto.getQuantity());

        Optional<Supplier> supplierOpt = supplierRepository.findByCode(dto.getSupplierCode());
        if (supplierOpt.isPresent()) {
            newProduct.setSupplier(supplierOpt.get());
        }

        Optional<Category> categoryOpt = categoryRepository.findByCode(dto.getCategoryCode());
        if(categoryOpt.isPresent()){
            newProduct.setCategory(categoryOpt.get());
        }

        Optional<GoldType> goldTypeOpt = goldTypeRepository.findByPercentage(dto.getGoldType());
        if (goldTypeOpt.isPresent()) {
            newProduct.setGoldType(goldTypeOpt.get());
        }

        String folderId = gDriveFolderManager
                .create(env.getProperty("jewelry.gdrive.folder.product"), dto.getSku());

        if(dto.getImages() != null) {
            List<String> imageIds = gDriveFileManager.uploadFile(folderId, dto.getImages());
            for(String imageId : imageIds) {
                newProduct.addImage(new Image(imageId));
            }
        }

        if(dto.getAvatar() != null) {
            String avatar = gDriveFileManager.uploadFile(folderId, Collections.singletonList(dto.getAvatar())).get(0);
            newProduct.setAvatar(avatar);
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
    public PagedResult<ProductProjection> findProducts(Pageable pageable) {
        Page<ProductProjection> result = productRepository.findProducts(pageable);
        return new PagedResult<>(
                result.getContent(),
                result.getTotalElements(),
                result.getTotalPages(),
                result.getNumber() + 1
        );
    }

    @Override
    public Page<ProductFilterDto> findProductsByFilters(ProductPageDto productPageDto,
                                                        ProductFilterCriteriaDto productFilterCriteriaDto){
        return productCriteriaRepository.findProductsByFilters(productPageDto, productFilterCriteriaDto);
    }
}