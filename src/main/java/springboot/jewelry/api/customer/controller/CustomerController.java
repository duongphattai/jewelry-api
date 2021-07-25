package springboot.jewelry.api.customer.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springboot.jewelry.api.commondata.model.ResponseHandler;
import springboot.jewelry.api.customer.dto.CustomerCreateDto;
import springboot.jewelry.api.customer.model.Customer;
import springboot.jewelry.api.customer.service.CustomerService;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private CustomerService service;

    @GetMapping("")
    public ResponseEntity<Object> findAll(){
        List<Customer> customers = service.findAll();
        if(customers.isEmpty()){
            return ResponseHandler.getResponse("Danh sách trống!", HttpStatus.OK);
        }

        return ResponseHandler.getResponse(customers, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Object> addCustomer(@Valid @RequestBody CustomerCreateDto dto,
                                          BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseHandler.getResponse(bindingResult, HttpStatus.BAD_REQUEST);
        }

        Customer customer = service.save(dto);

        return ResponseHandler.getResponse(customer, HttpStatus.OK);
    }

    @PutMapping("/{customer-id}")
    public ResponseEntity<Object> updateRole(@PathVariable("customer-id") Long id ,
                                             @Valid @RequestBody CustomerCreateDto dto ,
                                             BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return ResponseHandler.getResponse(HttpStatus.BAD_REQUEST);
        }

        Customer customer = service.updateCustomer(dto, id);

        return ResponseHandler.getResponse(customer, HttpStatus.OK);
    }
}
