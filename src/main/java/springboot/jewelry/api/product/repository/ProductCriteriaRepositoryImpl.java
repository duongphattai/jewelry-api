package springboot.jewelry.api.product.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import springboot.jewelry.api.product.converter.ProductConverter;
import springboot.jewelry.api.product.dto.ProductFilterCriteriaDto;
import springboot.jewelry.api.product.dto.ProductFilterDto;
import springboot.jewelry.api.product.dto.ProductPageDto;
import springboot.jewelry.api.product.model.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Repository
public class ProductCriteriaRepositoryImpl implements ProductCriteriaRepository{

    @Autowired
    private ProductRepository productRepository;

    private EntityManager entityManager;
    private CriteriaBuilder criteriaBuilder;

    public ProductCriteriaRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    @Override
    public Page<ProductFilterDto> findProductsByFilters(ProductPageDto productPageDto,
                                                        ProductFilterCriteriaDto productFilterCriteriaDto){
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> productRoot = criteriaQuery.from(Product.class);
        Predicate predicate = getPredicate(productFilterCriteriaDto, productRoot);
        criteriaQuery.where(predicate);
        setOrder(productPageDto, criteriaQuery, productRoot);

        TypedQuery<Product> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(productPageDto.getPageNumber() * productPageDto.getPageSize());
        typedQuery.setMaxResults(productPageDto.getPageSize());

        Pageable pageable = getPageable(productPageDto);

        long productsCount = getProductsCount(predicate);

        return new PageImpl<>(ProductConverter.convertToProductFilterDto(typedQuery.getResultList()), pageable, productsCount);
    }

    private Predicate getPredicate(ProductFilterCriteriaDto productFilterCriteriaDto, Root<Product> productRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if(Objects.nonNull(productFilterCriteriaDto.getName())){
            predicates.add(criteriaBuilder.like(productRoot.get(Product_.NAME),
                    "%" + productFilterCriteriaDto.getName() + "%"));
        }
        if(Objects.nonNull(productFilterCriteriaDto.getSku())){
            predicates.add(criteriaBuilder.equal(productRoot.get(Product_.SKU), productFilterCriteriaDto.getSku()));
        }
        if(Objects.isNull(productFilterCriteriaDto.getMinPrice())){
            productFilterCriteriaDto.setMinPrice(productRepository.minPrice());
        }
        if(Objects.isNull(productFilterCriteriaDto.getMaxPrice())){
            productFilterCriteriaDto.setMaxPrice(productRepository.maxPrice());
        }
        if(Objects.nonNull(productFilterCriteriaDto.getMinPrice())){
            predicates.add(criteriaBuilder.between(productRoot.get(Product_.PRICE),
                    productFilterCriteriaDto.getMinPrice(), productFilterCriteriaDto.getMaxPrice()));
        }
        if(Objects.nonNull(productFilterCriteriaDto.getCategory())){
            predicates.add(criteriaBuilder.equal(productRoot.get(Product_.CATEGORY).get(Category_.CODE),
                    productFilterCriteriaDto.getCategory()));
        }
        if(Objects.nonNull(productFilterCriteriaDto.getGoldType())){
            predicates.add(criteriaBuilder.equal(productRoot.get(Product_.GOLD_TYPE).get(GoldType_.PERCENTAGE),
                    productFilterCriteriaDto.getGoldType()));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

    }

    private void setOrder(ProductPageDto productPageDto, CriteriaQuery<Product> criteriaQuery, Root<Product> productRoot) {
        if(productPageDto.getSortDirection().equals(Sort.Direction.ASC)){
            criteriaQuery.orderBy(criteriaBuilder.asc(productRoot.get(productPageDto.getSortBy())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(productRoot.get(productPageDto.getSortBy())));
        }
    }

    private Pageable getPageable(ProductPageDto productPageDto) {
        Sort sort = Sort.by(productPageDto.getSortDirection(), productPageDto.getSortBy());
        return PageRequest.of(productPageDto.getPageNumber(), productPageDto.getPageSize(), sort);
    }

    private long getProductsCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Product> countRoot = countQuery.from(Product.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }

}
