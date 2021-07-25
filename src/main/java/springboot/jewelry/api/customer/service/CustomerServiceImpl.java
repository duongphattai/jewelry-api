package springboot.jewelry.api.customer.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import springboot.jewelry.api.customer.dto.CustomerCreateDto;
import springboot.jewelry.api.customer.repository.CustomerRepository;
import springboot.jewelry.api.commondata.GenericServiceImpl;
import springboot.jewelry.api.customer.model.Customer;
import springboot.jewelry.api.util.MapDtoToModel;


@AllArgsConstructor
@Service
public class CustomerServiceImpl extends GenericServiceImpl<Customer, Long> implements CustomerService {

    private CustomerRepository repository;
    private MapDtoToModel<Object, Customer> mapper;


    @Override
    public Customer save(CustomerCreateDto dto) {
        Customer customer = new Customer();
        customer = mapper.map(dto, customer);
        return repository.save(customer);
    }

    @Override
    public Customer updateCustomer(CustomerCreateDto dto, Long id) {
        Customer customer = repository.getOne(id);
        customer = mapper.map(dto, customer);
        return repository.save(customer);
    }
}
