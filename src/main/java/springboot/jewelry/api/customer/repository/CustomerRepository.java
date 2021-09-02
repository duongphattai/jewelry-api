package springboot.jewelry.api.customer.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springboot.jewelry.api.commondata.GenericRepository;
import springboot.jewelry.api.customer.model.Customer;

import java.util.List;
import java.util.Optional;


@Repository
public interface CustomerRepository extends GenericRepository<Customer, Long> {

    @Override
    @Query("SELECT c FROM Customer c JOIN FETCH c.roles")
    List<Customer> findAll();

    int countByPhoneNumber(String phoneNumber);

    int countByEmail(String email);

    Optional<Customer> findByEmail(String email);
}
