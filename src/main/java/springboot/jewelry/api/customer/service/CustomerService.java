package springboot.jewelry.api.customer.service;

import springboot.jewelry.api.commondata.GenericService;
import springboot.jewelry.api.customer.dto.CustomerChangePasswordDto;
import springboot.jewelry.api.customer.dto.CustomerCreateDto;
import springboot.jewelry.api.customer.dto.CustomerUpdateDto;
import springboot.jewelry.api.customer.model.Customer;

import java.util.Optional;


public interface CustomerService extends GenericService<Customer, Long> {
    Customer save(CustomerCreateDto dto);

    Customer updateCustomerInfo(CustomerUpdateDto dto, Long id);

    Optional<Customer> findByEmail(String email);

    Customer deactivateCustomerById(Long id);

    Customer activateCustomerById(Long id);

    Customer updateCustomerPassword(CustomerChangePasswordDto dto, Long id);
}
