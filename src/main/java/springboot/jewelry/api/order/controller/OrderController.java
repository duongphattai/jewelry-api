package springboot.jewelry.api.order.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.jewelry.api.commondata.model.ResponseHandler;
import springboot.jewelry.api.order.dto.OrderCreateDto;
import springboot.jewelry.api.order.dto.OrderDetailsDto;
import springboot.jewelry.api.order.service.itf.OrderService;
import springboot.jewelry.api.security.dto.CustomerPrincipalDto;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private OrderService orderService;

    @PostMapping("/mine")
    public ResponseEntity<Object> createNewOrder(@Valid @RequestBody OrderCreateDto dto,
                                                 @AuthenticationPrincipal CustomerPrincipalDto curUserDto,
                                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseHandler.getResponse(bindingResult, HttpStatus.BAD_REQUEST);
        }

        OrderDetailsDto orderDetailsDto = orderService.save(dto, curUserDto);
        return ResponseHandler.getResponse(orderDetailsDto, HttpStatus.OK);
    }
}
