package springboot.jewelry.api.product.service;

import lombok.AllArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import springboot.jewelry.api.commondata.GenericServiceImpl;
import springboot.jewelry.api.product.converter.ProductConverter;
import springboot.jewelry.api.product.dto.ProductCreateDto;
import springboot.jewelry.api.product.dto.ProductFilterDto;
import springboot.jewelry.api.product.model.*;
import springboot.jewelry.api.product.repository.GoldTypeRepository;
import springboot.jewelry.api.product.repository.ProductRepository;
import springboot.jewelry.api.product.repository.ProductTypeRepository;
import springboot.jewelry.api.product.service.itf.ProductService;
import springboot.jewelry.api.supplier.model.Supplier;
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
    private ProductTypeRepository productTypeRepository;
    private GoldTypeRepository goldTypeRepository;
    private MapDtoToModel<Object, Product> mapper;
    private EntityManager entityManager;

    @Override
    public Product save(ProductCreateDto dto) {
        Product newProduct = new Product();
        newProduct = mapper.map(dto, newProduct);
        newProduct.setTotalCostPrice(dto.getCostPrice() * dto.getQuantity());

        Optional<Supplier> supplierOpt = supplierRepository.findByCode(dto.getSupplierCode());
        if (supplierOpt.isPresent()) {
            newProduct.setSupplier(supplierOpt.get());
        }

        Optional<ProductType> productTypeOpt = productTypeRepository.findByCode(dto.getProductTypeCode());
        if (productTypeOpt.isPresent()) {
            newProduct.setProductType(productTypeOpt.get());
        }

        Optional<GoldType> goldTypeOpt = goldTypeRepository.findByPercentage(dto.getGoldType());
        if (goldTypeOpt.isPresent()) {
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
    public List<Product> findAllProductWithPage(int pageIndex, String sortBy) {
        Pageable pageable = PageRequest.of(pageIndex, 9, Sort.by(Sort.Direction.ASC, sortBy));
        return productRepository.findAllProductWithPage(pageable);
    }

    @Override
    public List<ProductFilterDto> findProductsByFilter(String name, String productType,
                                                       Double goldType, Double minPrice, Double maxPrice) {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery(Product.class);
            Root<Product> product = cq.from(Product.class);
            Predicate predicate = cb.conjunction();

            if(name != null){
                predicate = cb.and(predicate, cb.like(cb.lower(product.<String>get(Product_.NAME)), "%" + name.toLowerCase() + "%"));
            }
            if (productType != null) {
                predicate = cb.and(predicate, cb.equal(product.get(Product_.PRODUCT_TYPE).get(ProductType_.CODE), productType));
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
        } finally {
            entityManager.close();
        }
    }

}