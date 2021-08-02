package springboot.jewelry.api.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.jewelry.api.product.model.GoldType;

import java.util.Optional;

@Repository
public interface GoldTypeRepository extends JpaRepository<GoldType, Long> {

    Optional<GoldType> findByPercentage(Double goldType);

    int countByPercentage(Double percentage);
}
