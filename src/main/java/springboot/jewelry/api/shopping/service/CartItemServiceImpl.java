package springboot.jewelry.api.shopping.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import springboot.jewelry.api.commondata.GenericServiceImpl;
import springboot.jewelry.api.shopping.model.CartItem;
import springboot.jewelry.api.shopping.repository.CartItemRepository;
import springboot.jewelry.api.shopping.service.itf.CartItemService;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CartItemServiceImpl extends GenericServiceImpl<CartItem, Long> implements CartItemService {

    private CartItemRepository cartItemRepository;

    @Override
    public Optional<CartItem> findItemInCart(Long cartId, String productSku) {
        return cartItemRepository.findByCartIdAndProductSku(cartId, productSku);
    }
}
