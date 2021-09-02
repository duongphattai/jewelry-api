package springboot.jewelry.api.shopping.repository;

import org.springframework.stereotype.Repository;
import springboot.jewelry.api.commondata.GenericRepository;
import springboot.jewelry.api.shopping.model.CartItem;
import springboot.jewelry.api.shopping.projection.CartItemWithQuantityProjection;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends GenericRepository<CartItem, Long> {

    Optional<CartItem> findByCartIdAndProductSku(Long cartId, String productSku);

    Optional<List<CartItemWithQuantityProjection>> findCartItemsWithQuantityByCartId(Long cartId);
}
