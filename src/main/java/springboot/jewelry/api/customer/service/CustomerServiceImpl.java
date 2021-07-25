package springboot.jewelry.api.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.jewelry.api.customer.repository.CustomerRepository;
import springboot.jewelry.api.commondata.GenericServiceImpl;
import springboot.jewelry.api.customer.model.Customer;

@Service
public class CustomerServiceImpl extends GenericServiceImpl<Customer, Long> implements CustomerService {

    @Autowired
    private CustomerRepository repository;
}
