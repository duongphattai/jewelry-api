package springboot.jewelry.api.customer.controller;

import jdk.nashorn.internal.runtime.options.Option;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springboot.jewelry.api.commondata.model.ResponseHandler;
import springboot.jewelry.api.customer.dto.CustomerChangePasswordDto;
import springboot.jewelry.api.customer.dto.CustomerCreateDto;
import springboot.jewelry.api.customer.dto.CustomerUpdateDto;
import springboot.jewelry.api.customer.model.Customer;
import springboot.jewelry.api.customer.service.CustomerService;
import springboot.jewelry.api.customer.validation.annotation.CurrentCustomer;
import springboot.jewelry.api.security.dto.CustomerPrincipalDto;
import springboot.jewelry.api.security.exception.ResourceNotFoundException;
import springboot.jewelry.api.util.MessageUtils;

import javax.validation.Valid;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private CustomerService customerService;

    @GetMapping("/me")
    public ResponseEntity<Object> getCurrentCustomer(@CurrentCustomer CustomerPrincipalDto dto){
         Customer customer = customerService.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Tài khoản không tồn tại!"));

         return ResponseHandler.getResponse(customer, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> addCustomer(@Valid @RequestBody CustomerCreateDto dto,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseHandler.getResponse(bindingResult, HttpStatus.BAD_REQUEST);
        }

        Customer customer = customerService.save(dto);

        return ResponseHandler.getResponse(new MessageUtils("Đăng ký thành công!"), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateInfo(@CurrentCustomer CustomerPrincipalDto currentCustomer,
                                                 @Valid @RequestBody CustomerUpdateDto dto,
                                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseHandler.getResponse(HttpStatus.BAD_REQUEST);
        }

        Customer customer = customerService.updateCustomerInfo(dto, currentCustomer.getId());

        return ResponseHandler.getResponse(customer, HttpStatus.OK);
    }

    @PutMapping("/update/change-password")
    public ResponseEntity<Object> changePassword(@CurrentCustomer CustomerPrincipalDto currentCustomer,
                                                 @Valid @RequestBody CustomerChangePasswordDto dto,
                                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseHandler.getResponse(HttpStatus.BAD_REQUEST);
        }

        Customer customer = customerService.updateCustomerPassword(dto, currentCustomer.getId());

        return ResponseHandler.getResponse(customer, HttpStatus.OK);

    }

}
