package springboot.jewelry.api.product.repository;

import org.springframework.stereotype.Repository;
import springboot.jewelry.api.commondata.GenericRepository;
import springboot.jewelry.api.product.model.Category;

import java.util.Optional;

@Repository
public interface CategoryRepository extends GenericRepository<Category, Long> {

    Optional<Category> findByCode(String codeProductType);

    int countByCode(String productTypeCode);

}
