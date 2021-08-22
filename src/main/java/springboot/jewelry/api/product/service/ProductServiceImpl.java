package springboot.jewelry.api.product.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.*;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.jewelry.api.commondata.GenericServiceImpl;
import springboot.jewelry.api.commondata.GenericSpecificationImpl;
import springboot.jewelry.api.commondata.model.PagedResult;

import org.springframework.data.domain.Pageable;
import springboot.jewelry.api.commondata.model.SearchCriteria;
import springboot.jewelry.api.commondata.model.SearchOperation;
import springboot.jewelry.api.gdrive.manager.itf.GDriveFileManager;
import springboot.jewelry.api.gdrive.manager.itf.GDriveFolderManager;
import springboot.jewelry.api.product.dto.ProductCreateDto;
import springboot.jewelry.api.product.model.Image;
import springboot.jewelry.api.product.model.Product;
import springboot.jewelry.api.product.converter.ProductConverter;
import springboot.jewelry.api.product.dto.ProductFilterDto;
import springboot.jewelry.api.product.model.*;
import springboot.jewelry.api.product.projection.ProductDetailProjection;
import springboot.jewelry.api.product.projection.ProductProjection;
import springboot.jewelry.api.product.repository.GoldTypeRepository;
import springboot.jewelry.api.product.repository.ProductRepository;
import springboot.jewelry.api.product.repository.CategoryRepository;
import springboot.jewelry.api.product.service.itf.ProductService;
import springboot.jewelry.api.supplier.repository.SupplierRepository;
import springboot.jewelry.api.util.MapDtoToModel;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

@AllArgsConstructor
@Service
public class ProductServiceImpl extends GenericServiceImpl<Product, Long> implements ProductService {

    private ProductRepository productRepository;
    private SupplierRepository supplierRepository;
    private CategoryRepository categoryRepository;
    private GoldTypeRepository goldTypeRepository;

    private GDriveFolderManager gDriveFolderManager;
    private GDriveFileManager gDriveFileManager;
    private Environment env;
    private MapDtoToModel<Object, Product> mapper;
    private EntityManager entityManager;

    @Override
    @Transactional
    public ProductDetailProjection save(ProductCreateDto dto) {
        Product newProduct = new Product();
        newProduct = mapper.map(dto, newProduct);

        newProduct.setSupplier(supplierRepository.findByCode(dto.getSupplierCode()).get());

        newProduct.setCategory(categoryRepository.findByCode(dto.getCategoryCode()).get());

        newProduct.setGoldType(goldTypeRepository.findByPercentage(dto.getGoldType()).get());

        String folderId = gDriveFolderManager
                .create(env.getProperty("jewelry.gdrive.folder.product"), dto.getSku());

        if(dto.getImages() != null) {
            List<String> imageIds = gDriveFileManager.uploadFile(folderId, dto.getImages());
            for(String imageId : imageIds) {
                newProduct.addImage(new Image(imageId));
            }
        }
        System.out.println("images: " + newProduct.getImages());
        if(dto.getAvatar() != null) {
            String avatar = gDriveFileManager.uploadFile(folderId, Collections.singletonList(dto.getAvatar())).get(0);
            newProduct.setAvatar(avatar);
        }

        productRepository.save(newProduct);

        ProjectionFactory pf = new SpelAwareProxyProjectionFactory();
        return pf.createProjection(ProductDetailProjection.class, newProduct);
    }

    @Override
    public Product updateProductInfo(ProductCreateDto dto, Long id) {
        Product productUpdate = productRepository.getOne(id);
        productUpdate = mapper.map(dto, productUpdate);
        return productRepository.save(productUpdate);
    }

    @Override
    public PagedResult<ProductDetailProjection> findProducts(Pageable pageable) {
        Page<ProductDetailProjection> result = productRepository.findProductDetailBy(pageable);

        return new PagedResult<>(
                result.getContent(),
                result.getTotalElements(),
                result.getTotalPages(),
                result.getNumber() + 1
        );
    }

    @Override
    public PagedResult<ProductProjection> findProductsByNameAndSku(String searchValue, Pageable pageable) {
        GenericSpecificationImpl<ProductProjection> genericSpesification = new GenericSpecificationImpl<>();
        genericSpesification.add(new SearchCriteria("name", "huy", SearchOperation.MATCH));
        Page<ProductProjection> result = productRepository.findAll(genericSpesification, pageable);
        System.out.println("type: " + result.getContent());
        return new PagedResult<>(
                result.getContent(),
                result.getTotalElements(),
                result.getTotalPages(),
                result.getNumber() + 1
        );
    }

    @Override
    public List<ProductFilterDto> findProductsByFilter(String name, String category, Double goldType,
                                                       Double minPrice, Double maxPrice) {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery(Product.class);
            Root<Product> product = cq.from(Product.class);
            Predicate predicate = cb.conjunction();

            if (name != null) {
                predicate = cb.and(predicate, cb.like(cb.lower(product.<String>get(Product_.NAME)), "%" + name.toLowerCase() + "%"));
            }
            if (category != null) {
                predicate = cb.and(predicate, cb.equal(product.get(Product_.CATEGORY).get(Category_.CODE), category));
            }
            if (goldType != null) {
                predicate = cb.and(predicate, cb.equal(product.get(Product_.GOLD_TYPE).get(GoldType_.PERCENTAGE), goldType));
            }
            if (minPrice == null) {
                minPrice = productRepository.minPrice();
            }
            if (maxPrice == null) {
                maxPrice = productRepository.maxPrice();
            }
            if (minPrice != null && maxPrice != null) {
                predicate = cb.and(predicate, cb.between(product.get(Product_.PRICE), minPrice, maxPrice));
            }
            cq.where(predicate);
            TypedQuery<Product> query = entityManager.createQuery(cq);

           return ProductConverter.convertToProductFilterDto(query.getResultList());

        }
        finally {
            entityManager.close();
        }
    }


}