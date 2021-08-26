package springboot.jewelry.api.supplier.repository;

import org.springframework.stereotype.Repository;
import springboot.jewelry.api.commondata.GenericRepository;
import springboot.jewelry.api.supplier.model.Supplier;

import java.util.Optional;

@Repository
public interface SupplierRepository extends GenericRepository<Supplier, Long> {

    Optional<Supplier> findByCode(String code);

    int countByCode(String supplierCode);
}
