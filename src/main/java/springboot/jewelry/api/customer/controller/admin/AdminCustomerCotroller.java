package springboot.jewelry.api.customer.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.jewelry.api.commondata.model.ResponseHandler;
import springboot.jewelry.api.customer.service.CustomerService;
import springboot.jewelry.api.util.MessageUtils;

@RestController
@RequestMapping("/api/admin/customer")
public class AdminCustomerCotroller {

    @Autowired
    private CustomerService customerService;

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
}
