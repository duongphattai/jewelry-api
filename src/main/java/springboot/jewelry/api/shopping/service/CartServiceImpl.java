package springboot.jewelry.api.shopping.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.jewelry.api.commondata.GenericServiceImpl;
import springboot.jewelry.api.product.repository.ProductRepository;
import springboot.jewelry.api.shopping.converter.CartConverter;
import springboot.jewelry.api.shopping.dto.CartDetailsDto;
import springboot.jewelry.api.shopping.dto.CartItemCreateOrUpdateDto;
import springboot.jewelry.api.shopping.model.Cart;
import springboot.jewelry.api.shopping.model.CartItem;
import springboot.jewelry.api.shopping.projection.CartItemWithQuantityProjection;
import springboot.jewelry.api.shopping.projection.CartWithTotalProjection;
import springboot.jewelry.api.shopping.repository.CartItemRepository;
import springboot.jewelry.api.shopping.repository.CartRepository;
import springboot.jewelry.api.shopping.service.itf.CartService;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CartServiceImpl extends GenericServiceImpl<Cart, Long> implements CartService {

    private CartRepository cartRepository;
    private CartItemRepository cartItemRepository;
    private ProductRepository productRepository;

    @Override
    public Cart findCartByEmail(String email) {
        return cartRepository.findCartByCustomerEmail(email);
    }

    @Override
    public CartDetailsDto findCartDetails(String email) {
        CartWithTotalProjection cartWithTotalProjection
                = cartRepository.findCartWithTotalProjectionByCustomerEmail(email);
        Optional<List<CartItemWithQuantityProjection>> cartItemWithQuantityProjections
                = cartItemRepository.findCartItemsWithQuantityByCartId(cartWithTotalProjection.getId());

        return cartItemWithQuantityProjections.map(item -> CartConverter.toCartDetailsDto(cartWithTotalProjection.getTotal(), item))
                .orElse(null);

    }

    @Override
    public Optional<CartItem> findItemInCart(Cart cart, String productSku) {
        return cartItemRepository.findByCartIdAndProductSku(cart.getId(), productSku);
    }

    @Override
    @Transactional
    public CartDetailsDto addOrUpdateItemInCart(CartItemCreateOrUpdateDto itemDto, String email) {
        Cart cart = cartRepository.findCartByCustomerEmail(email);
        CartItem item = new CartItem();
        Optional<CartItem> itemOpt = cartItemRepository.findByCartIdAndProductSku(cart.getId(), itemDto.getProductSku());
        if(itemOpt.isPresent()) {
            item = itemOpt.get();
            item.setQuantity(item.getQuantity() + itemDto.getQuantity());
            item.setCart(cart);
        } else {
            item.setProduct(productRepository.findBySku(itemDto.getProductSku()).get());
            item.setQuantity(itemDto.getQuantity());
            cart.addItem(item);
        }

        //item.getProduct().setQuantity(item.getProduct().getQuantity() - itemDto.getQuantity());
        cart.setTotal(cart.getTotal() + (item.getProduct().getPrice() * itemDto.getQuantity()));
        return CartConverter.toCartDetailsDto(cartRepository.save(cart));
    }

    @Override
    @Transactional
    public CartDetailsDto updateItemInCart(Cart cart, CartItem item, Integer quantity) {
        int diffQuantity = quantity - item.getQuantity();
        item.setCart(cart);
        item.setQuantity(quantity);
        //item.getProduct().setQuantity(item.getProduct().getQuantity() - diffQuantity);
        cart.setTotal(cart.getTotal() + (diffQuantity * item.getProduct().getPrice()));
        return CartConverter.toCartDetailsDto(cartRepository.save(cart));
    }

    @Override
    public CartDetailsDto deleteItemInCart(Cart cart, CartItem item) {
        cart.removeItem(item);
        cart.setTotal(cart.getTotal() - (item.getQuantity() * item.getProduct().getPrice()));
        return CartConverter.toCartDetailsDto(cartRepository.save(cart));
    }

}
