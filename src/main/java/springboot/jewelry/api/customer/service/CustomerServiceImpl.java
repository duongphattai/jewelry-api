package springboot.jewelry.api.customer.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import springboot.jewelry.api.customer.dto.CustomerChangePasswordDto;
import springboot.jewelry.api.customer.dto.CustomerCreateDto;
import springboot.jewelry.api.customer.dto.CustomerUpdateDto;
import springboot.jewelry.api.customer.repository.CustomerRepository;
import springboot.jewelry.api.commondata.GenericServiceImpl;
import springboot.jewelry.api.customer.model.Customer;
import springboot.jewelry.api.role.model.Role;
import springboot.jewelry.api.role.model.RoleName;
import springboot.jewelry.api.role.repository.RoleRepository;
import springboot.jewelry.api.util.MapDtoToModel;

import java.util.*;


@AllArgsConstructor
@Service
public class CustomerServiceImpl extends GenericServiceImpl<Customer, Long> implements CustomerService {

    private CustomerRepository customerRepository;
    private MapDtoToModel<Object, Customer> mapper;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public Customer save(CustomerCreateDto dto) {
        Customer newCustomer = new Customer();
        newCustomer = mapper.map(dto, newCustomer);
        newCustomer.setEmail(dto.getEmail().toLowerCase());
        newCustomer.setPassword(passwordEncoder.encode(dto.getPassword()));

        Set<RoleName> strRoles = new HashSet<>(EnumSet.allOf(RoleName.class));
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            Optional<Role> userRole = roleRepository.findByRoleName(RoleName.ROLE_USER);
            roles.add(userRole.get());
        });

        newCustomer.setRoles(roles);
        newCustomer.activate();
        return customerRepository.save(newCustomer);
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    // WITH ROLE ADMIN
    @Override
    public Customer deactivateCustomerById(Long id) {
       Customer customer = customerRepository.getOne(id);
       customer.setActive(false);
       return customerRepository.save(customer);
    }

    // WITH ROLE ADMIN
    @Override
    public Customer activateCustomerById(Long id) {
        Customer customer = customerRepository.getOne(id);
        customer.setActive(true);
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomerPassword(CustomerChangePasswordDto dto, Long id) {
        Customer customerUpdate = customerRepository.getOne(id);
        customerUpdate.setPassword(passwordEncoder.encode(dto.getPassword()));
        return customerRepository.save(customerUpdate);
    }

    @Override
    public Customer updateCustomerInfo(CustomerUpdateDto dto, Long id) {
        Customer customerUpdate = customerRepository.getOne(id);
        customerUpdate = mapper.map(dto, customerUpdate);
        return customerRepository.save(customerUpdate);
    }
}
