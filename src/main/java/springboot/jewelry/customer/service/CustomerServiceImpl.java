package springboot.jewelry.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.jewelry.commondata.GenericServiceImpl;
import springboot.jewelry.customer.model.Customer;
import springboot.jewelry.customer.repository.CustomerRepository;

@Service
public class CustomerServiceImpl extends GenericServiceImpl<Customer, Long> implements CustomerService {

    @Autowired
    private CustomerRepository repository;
}
