package springboot.jewelry.api.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springboot.jewelry.api.customer.model.Customer;
import springboot.jewelry.api.customer.projection.CustomerProjection;

import java.util.List;
import java.util.Optional;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByUsername(String username);

    @Query("SELECT c.username AS username, c.password AS password, c.role.roleName AS roleRoleName FROM Customer c")
    List<CustomerProjection> findCustomerWithAllRoleName();
}
