package springboot.jewelry.api.shopping.service.itf;

import springboot.jewelry.api.commondata.GenericService;
import springboot.jewelry.api.shopping.model.CartItem;

import java.util.Optional;

public interface CartItemService extends GenericService<CartItem, Long> {

    Optional<CartItem> findItemInCart(Long cartId, String productSku);
}
