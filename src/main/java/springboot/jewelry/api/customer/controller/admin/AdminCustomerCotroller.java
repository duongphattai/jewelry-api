package springboot.jewelry.api.customer.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.jewelry.api.commondata.model.ResponseHandler;
import springboot.jewelry.api.customer.model.Customer;
import springboot.jewelry.api.customer.service.CustomerService;
import springboot.jewelry.api.util.MessageUtils;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/customer")
public class AdminCustomerCotroller {

    @Autowired
    private CustomerService customerService;

    @GetMapping("")
    public ResponseEntity<Object> findAll() {
        List<Customer> customers = customerService.findAll();
        if (customers.isEmpty()) {
            return ResponseHandler.getResponse(new MessageUtils("Danh sách trống!"), HttpStatus.OK);
        }

        return ResponseHandler.getResponse(customers, HttpStatus.OK);
    }

    @PutMapping("by-id/{id}/activate")
    public ResponseEntity<Object> activateCustomerById(@PathVariable(value = "id") Long id){
        if(customerService.findById(id).isPresent()){
            customerService.activateCustomerById(id);
            return ResponseHandler.getResponse(new MessageUtils("Kích hoạt tài khoản thành công!"), HttpStatus.OK);
        }
        return ResponseHandler.getResponse(
                new MessageUtils("Không tìm thấy tài khoản có ID: " + id), HttpStatus.BAD_REQUEST);
    }

    @PutMapping("by-id/{id}/deactivate")
    public ResponseEntity<Object> deactivateCustomerById(@PathVariable(value = "id") Long id){
        if(customerService.findById(id).isPresent()){
            customerService.deactivateCustomerById(id);
            return ResponseHandler.getResponse(new MessageUtils("Khóa tài khoản thành công!"), HttpStatus.OK);
        }
        return ResponseHandler.getResponse(
                new MessageUtils("Không tìm thấy tài khoản có ID: " + id), HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{customer-id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable("customer-id") Long id) {

        Optional<Customer> customer = customerService.findById(id);
        if (!customer.isPresent()) {
            return ResponseHandler.getResponse(new MessageUtils("Không tìm thấy tài khoản có ID: " + id), HttpStatus.OK);
        }

        customerService.deleteById(id);

        return ResponseHandler.getResponse(HttpStatus.OK);
    }
}
