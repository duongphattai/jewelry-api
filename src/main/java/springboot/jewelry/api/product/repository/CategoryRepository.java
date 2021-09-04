package springboot.jewelry.api.product.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import springboot.jewelry.api.commondata.GenericRepository;
import springboot.jewelry.api.product.model.Category;

import java.util.Optional;
import java.util.Set;

@Repository
public interface CategoryRepository extends GenericRepository<Category, Long> {

    Optional<Category> findByCode(String code);

    int countByCode(String productTypeCode);

    Category findNameById(Long id);

}
