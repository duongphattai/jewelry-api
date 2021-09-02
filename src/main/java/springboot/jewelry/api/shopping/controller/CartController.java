package springboot.jewelry.api.shopping.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springboot.jewelry.api.commondata.model.ResponseHandler;
import springboot.jewelry.api.product.service.itf.ProductService;
import springboot.jewelry.api.security.dto.CustomerPrincipalDto;
import springboot.jewelry.api.shopping.dto.CartDetailsDto;
import springboot.jewelry.api.shopping.dto.CartItemCreateOrUpdateDto;
import springboot.jewelry.api.shopping.model.Cart;
import springboot.jewelry.api.shopping.model.CartItem;
import springboot.jewelry.api.shopping.service.itf.CartItemService;
import springboot.jewelry.api.shopping.service.itf.CartService;


import javax.validation.Valid;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/carts")
public class CartController {

    private CartService cartService;

    @GetMapping("/mine")
    public ResponseEntity<Object> findCartDetails(@AuthenticationPrincipal CustomerPrincipalDto curUser) {
        CartDetailsDto cartDetailsDto = cartService.findCartDetails(curUser.getEmail());
        if(cartDetailsDto.getItems() == null) {
            return ResponseHandler.getResponse("Không có sản phẩm trong giỏ hàng", HttpStatus.OK);
        }
        return ResponseHandler.getResponse(cartDetailsDto, HttpStatus.OK);
    }

    @PutMapping("/mine/items")
    public ResponseEntity<Object> addOrUpdateItemInCart(@Valid @RequestBody CartItemCreateOrUpdateDto itemDto,
                                                        @AuthenticationPrincipal CustomerPrincipalDto curUser,
                                                        BindingResult bindingResult) {
        CartDetailsDto cartDetailsDto = cartService.addOrUpdateItemInCart(itemDto, curUser.getEmail());
        return ResponseHandler.getResponse(cartDetailsDto, HttpStatus.OK);
    }

    @PutMapping("/mine/items/{productSku}")
    public ResponseEntity<Object> updateItemInCart(@PathVariable String productSku,
                                                   @RequestParam("quantity") Integer quantity,
                                                   @AuthenticationPrincipal CustomerPrincipalDto curUser) {
        Cart cart = cartService.findCartByEmail(curUser.getEmail());
        Optional<CartItem> itemOpt = cartService.findItemInCart(cart, productSku);
        if(!itemOpt.isPresent()) {
            return ResponseHandler.getResponse("Sản phẩm không tồn tại", HttpStatus.BAD_REQUEST);
        } else {
            int stock = itemOpt.get().getQuantity() + itemOpt.get().getProduct().getQuantity();
            if(stock < quantity) {
                return ResponseHandler.getResponse("Số lượng sản phẩm phải nhỏ hơn " + stock, HttpStatus.ACCEPTED);
            }
        }

        CartDetailsDto cartDetailsDto = cartService.updateItemInCart(cart, itemOpt.get(), quantity);
        return ResponseHandler.getResponse(cartDetailsDto, HttpStatus.OK);
    }


    @DeleteMapping("/mine/items/{productSku}")
    public ResponseEntity<Object> deleteItemInCart(@PathVariable String productSku,
                                                   @AuthenticationPrincipal CustomerPrincipalDto curUser) {
        Cart cart = cartService.findCartByEmail(curUser.getEmail());
        Optional<CartItem> itemOpt = cartService.findItemInCart(cart, productSku);
        if(!itemOpt.isPresent()) {
            return ResponseHandler.getResponse("Sản phẩm không tồn tại!", HttpStatus.BAD_REQUEST);
        }

        CartDetailsDto cartDetailsDto = cartService.deleteItemInCart(cart, itemOpt.get());
        return ResponseHandler.getResponse(cartDetailsDto, HttpStatus.OK);
    }
}
