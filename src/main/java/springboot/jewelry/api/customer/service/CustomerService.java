package springboot.jewelry.api.customer.service;

import springboot.jewelry.api.commondata.GenericService;
import springboot.jewelry.api.customer.dto.CustomerCreateDto;
import springboot.jewelry.api.customer.dto.CustomerUpdateDto;
import springboot.jewelry.api.customer.model.Customer;
import springboot.jewelry.api.customer.projection.CustomerProjection;

import java.util.List;
import java.util.Optional;

public interface CustomerService extends GenericService<Customer, Long> {
    Customer save(CustomerCreateDto dto);

    Customer updateCustomer(CustomerUpdateDto dto, Long id);

    List<CustomerProjection> findCustomerWithAllRoleName();

    boolean isTakenUsername(String username);

    boolean isTakenMobileNo(String mobileNo);

    boolean isTakenEmail(String email);

}
