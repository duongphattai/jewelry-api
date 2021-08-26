package springboot.jewelry.api.customer.repository;

import org.springframework.stereotype.Repository;
import springboot.jewelry.api.commondata.GenericRepository;
import springboot.jewelry.api.customer.model.Customer;

import java.util.Optional;


@Repository
public interface CustomerRepository extends GenericRepository<Customer, Long> {

    int countByPhoneNumber(String phoneNumber);

    int countByEmail(String email);

    Optional<Customer> findByEmail(String email);
}
