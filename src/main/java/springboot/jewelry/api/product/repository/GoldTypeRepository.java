package springboot.jewelry.api.product.repository;

import org.springframework.stereotype.Repository;
import springboot.jewelry.api.commondata.GenericRepository;
import springboot.jewelry.api.product.model.GoldType;

import java.util.Optional;

@Repository
public interface GoldTypeRepository extends GenericRepository<GoldType, Long> {

    Optional<GoldType> findByPercentage(Double percentage);

    int countByPercentage(Double percentage);
}
