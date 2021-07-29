package springboot.jewelry.api.customer.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import springboot.jewelry.api.customer.dto.CustomerCreateDto;
import springboot.jewelry.api.customer.dto.CustomerUpdateDto;
import springboot.jewelry.api.customer.projection.CustomerProjection;
import springboot.jewelry.api.customer.repository.CustomerRepository;
import springboot.jewelry.api.commondata.GenericServiceImpl;
import springboot.jewelry.api.customer.model.Customer;
import springboot.jewelry.api.role.model.Role;
import springboot.jewelry.api.role.repository.RoleRepository;
import springboot.jewelry.api.util.MapDtoToModel;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Service
public class CustomerServiceImpl extends GenericServiceImpl<Customer, Long> implements CustomerService {

    private CustomerRepository customerRepository;
    private MapDtoToModel<Object, Customer> mapper;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;


    @Override
    public Customer save(CustomerCreateDto dto) {
        Customer customer = new Customer();
        customer = mapper.map(dto, customer);
        customer.setPassword(passwordEncoder.encode(dto.getPassword()));

        // Set value = 1L để lấy Role mặc định là "USER"
        Optional<Role> role = roleRepository.findById(1L);
        customer.setRole(role.get());
        return customerRepository.save(customer);
    }

    @Override
    public List<CustomerProjection> findCustomerWithAllRoleName() {
        return customerRepository.findCustomerWithAllRoleName();
    }

    @Override
    public boolean isTakenUsername(String username) {
        return customerRepository.countByUsername(username) >= 1;
    }

    @Override
    public boolean isTakenPhoneNumber(String phoneNumber) {
        return customerRepository.countByPhoneNumber(phoneNumber) >= 1;
    }

    @Override
    public boolean isTakenEmail(String email) {
        return customerRepository.countByEmail(email) >= 1;
    }

    @Override
    public Customer updateCustomerInfo(CustomerUpdateDto dto, Long id) {
        Customer customer = customerRepository.getOne(id);
        customer = mapper.map(dto, customer);
        return customerRepository.save(customer);

    }
}
