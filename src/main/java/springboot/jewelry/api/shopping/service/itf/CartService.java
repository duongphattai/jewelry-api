package springboot.jewelry.api.shopping.service.itf;

import springboot.jewelry.api.commondata.GenericService;
import springboot.jewelry.api.shopping.dto.CartDetailsDto;
import springboot.jewelry.api.shopping.dto.CartItemCreateOrUpdateDto;
import springboot.jewelry.api.shopping.model.Cart;
import springboot.jewelry.api.shopping.model.CartItem;

import java.util.Optional;

public interface CartService extends GenericService<Cart, Long> {

    Cart findCartByEmail(String email);

    CartDetailsDto findCartDetails(String email);

    Optional<CartItem> findItemInCart(Cart cart, String productSku);

    CartDetailsDto addOrUpdateItemInCart(CartItemCreateOrUpdateDto itemDto, String email);

    CartDetailsDto updateItemInCart(Cart cart, CartItem item, Integer quantity);

    CartDetailsDto deleteItemInCart(Cart cart, CartItem item);
}
