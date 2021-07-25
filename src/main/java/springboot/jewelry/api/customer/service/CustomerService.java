package springboot.jewelry.api.customer.service;

import springboot.jewelry.api.commondata.GenericService;
import springboot.jewelry.api.customer.dto.CustomerCreateDto;
import springboot.jewelry.api.customer.model.Customer;

public interface CustomerService extends GenericService<Customer, Long> {
    Customer save(CustomerCreateDto dto);

    Customer updateCustomer(CustomerCreateDto dto, Long id);
}
