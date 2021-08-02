package springboot.jewelry.api.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.jewelry.api.product.model.ProductType;

import java.util.Optional;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {

    Optional<ProductType> findByCode(String codeProductType);

    int countByCode(String productTypeCode);
}
