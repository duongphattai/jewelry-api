package springboot.jewelry.api.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springboot.jewelry.api.commondata.GenericRepository;
import springboot.jewelry.api.customer.model.Customer;
import springboot.jewelry.api.customer.projection.CustomerProjection;

import java.util.List;
import java.util.Optional;


@Repository
public interface CustomerRepository extends GenericRepository<Customer, Long> {

    Optional<Customer> findByUsername(String username);

    @Query("SELECT c.username AS username, c.role.roleName AS roleName FROM Customer c")
    List<CustomerProjection> findCustomerWithAllRoleName();

    int countByUsername(String username);

    int countByPhoneNumber(String phoneNumber);

    int countByEmail(String email);

}
