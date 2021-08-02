package springboot.jewelry.api.customer.service;

import springboot.jewelry.api.commondata.GenericService;
import springboot.jewelry.api.customer.dto.CustomerCreateDto;
import springboot.jewelry.api.customer.dto.CustomerUpdateDto;
import springboot.jewelry.api.customer.model.Customer;
import springboot.jewelry.api.customer.projection.CustomerProjection;

import java.util.List;


public interface CustomerService extends GenericService<Customer, Long> {
    Customer save(CustomerCreateDto dto);

    Customer updateCustomerInfo(CustomerUpdateDto dto, Long id);

    List<CustomerProjection> findCustomerWithAllRoleName();
}
