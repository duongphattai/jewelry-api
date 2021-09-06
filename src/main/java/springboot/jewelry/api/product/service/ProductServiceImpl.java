package springboot.jewelry.api.product.service;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
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
import springboot.jewelry.api.product.dto.*;
import springboot.jewelry.api.product.model.Image;
import springboot.jewelry.api.product.model.Product;
import springboot.jewelry.api.product.converter.ProductConverter;

import springboot.jewelry.api.product.projection.ProductDetailsAdminProjection;

import springboot.jewelry.api.product.projection.ProductDetailsProjection;
import springboot.jewelry.api.product.projection.ProductSummaryProjection;
import springboot.jewelry.api.product.projection.ShortProductProjection;
import springboot.jewelry.api.product.repository.GoldTypeRepository;
import springboot.jewelry.api.product.repository.ImageRepository;
import springboot.jewelry.api.product.repository.ProductRepository;
import springboot.jewelry.api.product.repository.CategoryRepository;

import springboot.jewelry.api.product.service.itf.ProductService;
import springboot.jewelry.api.supplier.repository.SupplierRepository;
import springboot.jewelry.api.util.MapDtoToModel;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.*;

@AllArgsConstructor
@Service
public class ProductServiceImpl extends GenericServiceImpl<Product, Long> implements ProductService {

    private ProductRepository productRepository;
    private SupplierRepository supplierRepository;
    private CategoryRepository categoryRepository;
    private GoldTypeRepository goldTypeRepository;
    private ImageRepository imageRepository;

    private GDriveFolderManager gDriveFolderManager;
    private GDriveFileManager gDriveFileManager;
    private Environment env;
    private MapDtoToModel<Object, Product> mapper;

    @Override
    @Transactional
    public ProductDetailsDto save(ProductCreateDto dto) {
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

        if(dto.getAvatar() != null) {
            String avatar = gDriveFileManager.uploadFile(folderId, Collections.singletonList(dto.getAvatar())).get(0);
            newProduct.setAvatar(avatar);
        }

        productRepository.save(newProduct);

        return ProductConverter.entityToProductDetailDto(newProduct);
    }

    @SneakyThrows
    @Override
    @Transactional
    public Product updateProductInfo(ProductUpdateDto dto, Long id) {
        Product productUpdate = productRepository.getOne(id);

        productUpdate = mapper.map(dto, productUpdate);

        productUpdate.setSlug(new Slug().slugify(productUpdate.getName() + " " + productUpdate.getSku()));

        productUpdate.setSupplier(supplierRepository.findByCode(dto.getSupplierCode()).get());

        productUpdate.setCategory(categoryRepository.findByCode(dto.getCategoryCode()).get());

        productUpdate.setGoldType(goldTypeRepository.findByPercentage(dto.getGoldType()).get());

        if(dto.getImagesRemoved() != null) {
            gDriveFileManager.deleteFile(dto.getImagesRemoved());
            Set<Image> images = imageRepository.findByGDriveIdIn(dto.getImagesRemoved());
            for(Image image : images) {
                productUpdate.removeImage(image);
            }
        }

        if(dto.getNewImages() != null) {
            String folderId = gDriveFolderManager
                    .findIdByName(env.getProperty("jewelry.gdrive.folder.product"), dto.getSku());
            List<String> imageIds = gDriveFileManager.uploadFile(folderId, dto.getNewImages());
            for(String imageId : imageIds) {
                productUpdate.addImage(new Image(imageId));
            }
        }

        if(dto.getAvatar() != null) {
            gDriveFileManager.deleteFile(Collections.singletonList(productUpdate.getAvatar()));
            String folderId = gDriveFolderManager
                    .findIdByName(env.getProperty("jewelry.gdrive.folder.product"), dto.getSku());
            String avatar = gDriveFileManager.uploadFile(folderId, Collections.singletonList(dto.getAvatar())).get(0);
            productUpdate.setAvatar(avatar);
        }

        return productRepository.save(productUpdate);
    }

    @Override
    public PagedResult<ProductSummaryDto> findProductsSummary(Pageable pageable) {
        Page<ProductSummaryProjection> productsSummaryProjectionPaged = productRepository.findProductsSummaryBy(pageable);

        return new PagedResult<>(
                ProductConverter.projectionToProductSummaryDto(productsSummaryProjectionPaged.getContent()),
                productsSummaryProjectionPaged.getTotalElements(),
                productsSummaryProjectionPaged.getTotalPages(),
                productsSummaryProjectionPaged.getNumber() + 1
        );
    }

    @Override
    public PagedResult<ProductSummaryDto> findProductsSummaryWithSearch(SearchCriteria searchCriteria, Pageable pageable) {
        SearchSpecification<Product> productSearchSpecification = new SearchSpecification<>(searchCriteria);

        Page<Product> productsPaged = productRepository.findAll(productSearchSpecification, pageable);

        return new PagedResult<>(
                ProductConverter.entityToProductSummaryDto(productsPaged.getContent()),
                productsPaged.getTotalElements(),
                productsPaged.getTotalPages(),
                productsPaged.getNumber() + 1
        );
    }

    @Override
    public PagedResult<ShortProductDto> findShortProductsWithSearch(SearchCriteria searchCriteria, Pageable pageable) {
        SearchSpecification<Product> productSearchSpecification = new SearchSpecification<>(searchCriteria);
        Page<Product> productsPaged = productRepository.findAll(productSearchSpecification, pageable);
        return new PagedResult<>(
                ProductConverter.entityToShortProductDto(productsPaged.getContent()),
                productsPaged.getTotalElements(),
                productsPaged.getTotalPages(),
                productsPaged.getNumber() + 1
        );
    }

    @Override
    public PagedResult<ShortProductDto> findShortProducts(Pageable pageable) {
        Page<ShortProductProjection> shortProductsPaged = productRepository.findShortProductsBy(pageable);

        return new PagedResult<>(
                ProductConverter.projectionToShortProductDto(shortProductsPaged.getContent()),
                shortProductsPaged.getTotalElements(),
                shortProductsPaged.getTotalPages(),
                shortProductsPaged.getNumber() + 1
        );
    }

    @Override
    public PagedResult<ShortProductDto> findShortProductsByCategory(String categorySlug, Pageable pageable) {
        Page<ShortProductProjection> shortProductsPaged
                = productRepository.findShortProductsByCategorySlug(categorySlug, pageable);

        return new PagedResult<>(
                ProductConverter.projectionToShortProductDto(shortProductsPaged.getContent()),
                shortProductsPaged.getTotalElements(),
                shortProductsPaged.getTotalPages(),
                shortProductsPaged.getNumber() + 1
        );
    }

    @Override
    public ProductDetailsDto findProductDetails(String slug) {
        Optional<ProductDetailsProjection> productDetailsProjection = productRepository.findProductDetailsBySlug(slug);
        if(productDetailsProjection.isPresent()) {
            Set<String> images = imageRepository.findGDriveIdByProductSku(productDetailsProjection.get().getSku());
            return ProductConverter.projectionToProductDetailDto(productDetailsProjection.get(), images);
        }
        return null;
    }

    @Override
    public ProductDetailsAdminDto findProductById(Long id) {
        Optional<ProductDetailsAdminProjection> productDetailsAdminProjection = productRepository
                                                                .findProductDetailsAdminById(id);
        if(productDetailsAdminProjection.isPresent()) {
            Set<String> images = imageRepository.findGDriveIdByProductSku(productDetailsAdminProjection.get().getSku());
            return ProductConverter.projectionToProductDetailsAdminDto(productDetailsAdminProjection.get(), images);
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Product product = productRepository.getOne(id);
        String folderId = gDriveFolderManager
                .findIdByName(env.getProperty("jewelry.gdrive.folder.product"), product.getSku());
        gDriveFolderManager.deleteFolder(folderId);
        super.deleteById(id);
    }
}