package springboot.jewelry.api.shopping.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springboot.jewelry.api.commondata.GenericRepository;
import springboot.jewelry.api.shopping.model.Cart;
import springboot.jewelry.api.shopping.projection.CartWithTotalProjection;

import java.util.Optional;

@Repository
public interface CartRepository extends GenericRepository<Cart, Long> {

    //@Query(value = "SELECT c FROM Cart c WHERE c.customer.email = ?1")
    Cart findCartByCustomerEmail(String email);

    //@Query(value = "SELECT c FROM Cart c")
    CartWithTotalProjection findCartWithTotalProjectionByCustomerEmail(String customerEmail);
}
