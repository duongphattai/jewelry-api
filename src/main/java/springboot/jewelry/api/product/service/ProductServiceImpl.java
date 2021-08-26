package springboot.jewelry.api.product.service;

import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.jewelry.api.commondata.GenericServiceImpl;
import springboot.jewelry.api.commondata.SearchSpecification;
import springboot.jewelry.api.commondata.Slug;
import springboot.jewelry.api.commondata.model.PagedResult;

import org.springframework.data.domain.Pageable;



import springboot.jewelry.api.commondata.model.SearchCriteria;
import springboot.jewelry.api.gdrive.manager.itf.GDriveFileManager;
import springboot.jewelry.api.gdrive.manager.itf.GDriveFolderManager;
import springboot.jewelry.api.product.dto.ProductCreateDto;
import springboot.jewelry.api.product.dto.ProductDetailDto;
import springboot.jewelry.api.product.dto.ProductSummaryDto;
import springboot.jewelry.api.product.model.Image;
import springboot.jewelry.api.product.model.Product;
import springboot.jewelry.api.product.converter.ProductConverter;
import springboot.jewelry.api.product.dto.ProductFilterDto;
import springboot.jewelry.api.product.model.*;
import springboot.jewelry.api.product.projection.ProductSummaryProjection;
import springboot.jewelry.api.product.repository.GoldTypeRepository;
import springboot.jewelry.api.product.repository.ProductRepository;
import springboot.jewelry.api.product.repository.CategoryRepository;

import springboot.jewelry.api.product.service.itf.ProductService;
import springboot.jewelry.api.supplier.repository.SupplierRepository;
import springboot.jewelry.api.util.MapDtoToModel;

import java.util.*;
import java.util.stream.Collectors;

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
    @Transactional
    public ProductDetailDto save(ProductCreateDto dto) {
        Product newProduct = new Product();

        newProduct = mapper.map(dto, newProduct);

        newProduct.setSlug(new Slug().slugify(newProduct.getName() + " " + newProduct.getSku()));

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

        return ProductConverter.toProductDetailDto(newProduct);
    }

    @Override
    public Product updateProductInfo(ProductCreateDto dto, Long id) {
        Product productUpdate = productRepository.getOne(id);
        productUpdate = mapper.map(dto, productUpdate);
        return productRepository.save(productUpdate);
    }

    @Override
    public PagedResult<ProductSummaryProjection> findProductsSummary(Pageable pageable) {
        Page<ProductSummaryProjection> productsSummaryPaged = productRepository.findProductDetailBy(pageable);

        return new PagedResult<>(
                productsSummaryPaged.getContent(),
                productsSummaryPaged.getTotalElements(),
                productsSummaryPaged.getTotalPages(),
                productsSummaryPaged.getNumber() + 1
        );
    }

    @Override
    public PagedResult<ProductSummaryDto> findProductsSummaryWithSearch(SearchCriteria searchCriteria, Pageable pageable) {
        SearchSpecification<Product> productSearchSpecification = new SearchSpecification<>(searchCriteria);

        Page<Product> productsPaged = productRepository.findAll(productSearchSpecification, pageable);

        return new PagedResult<>(
                ProductConverter.toProductSummaryDto(productsPaged.getContent()),
                productsPaged.getTotalElements(),
                productsPaged.getTotalPages(),
                productsPaged.getNumber() + 1
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

           return ProductConverter.toProductFilterDto(query.getResultList());

        }
        finally {
            entityManager.close();
        }
    }
}