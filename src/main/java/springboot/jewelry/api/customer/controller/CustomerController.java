package springboot.jewelry.api.customer.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springboot.jewelry.api.commondata.model.ResponseHandler;
import springboot.jewelry.api.customer.dto.CustomerCreateDto;
import springboot.jewelry.api.customer.dto.CustomerUpdateDto;
import springboot.jewelry.api.customer.model.Customer;
import springboot.jewelry.api.customer.projection.CustomerProjection;
import springboot.jewelry.api.customer.service.CustomerService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private CustomerService customerService;

    @GetMapping("")
    public ResponseEntity<Object> findAll() {
        List<Customer> customers = customerService.findAll();
        if (customers.isEmpty()) {
            return ResponseHandler.getResponse("Danh sách trống!", HttpStatus.OK);
        }

        return ResponseHandler.getResponse(customers, HttpStatus.OK);
    }

    @GetMapping("/all-role-name")
    public ResponseEntity<Object> findWithAllRoleName() {
        List<CustomerProjection> customers = customerService.findCustomerWithAllRoleName();
        if (customers.isEmpty()) {
            return ResponseHandler.getResponse("Danh sách trống!", HttpStatus.OK);
        }

        return ResponseHandler.getResponse(customers, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Object> addCustomer(@Valid @RequestBody CustomerCreateDto dto,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseHandler.getResponse(bindingResult, HttpStatus.BAD_REQUEST);
        }

        Customer customer = customerService.save(dto);

        return ResponseHandler.getResponse(customer, HttpStatus.OK);
    }

    @PutMapping("/{customer-id}")
    public ResponseEntity<Object> updateCustomer(@PathVariable("customer-id") Long id,
                                                 @Valid @RequestBody CustomerUpdateDto dto,
                                                 BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseHandler.getResponse(HttpStatus.BAD_REQUEST);
        }

        Customer customer = customerService.updateCustomerInfo(dto, id);

        return ResponseHandler.getResponse(customer, HttpStatus.OK);
    }

    @DeleteMapping("/{customer-id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable("customer-id") Long id) {

        Optional<Customer> customer = customerService.findById(id);
        if (!customer.isPresent()) {
            return ResponseHandler.getResponse("Không tìm thấy ID: " + id, HttpStatus.OK);
        }

        customerService.deleteById(id);

        return ResponseHandler.getResponse(HttpStatus.OK);
    }
}
