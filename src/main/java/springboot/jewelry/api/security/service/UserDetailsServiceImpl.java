package springboot.jewelry.api.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import springboot.jewelry.api.security.dto.UserDetailsDto;
import springboot.jewelry.api.customer.model.Customer;
import springboot.jewelry.api.customer.repository.CustomerRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CustomerRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Customer> customer = repository.findByEmail(email);
        if (!customer.isPresent())
            throw new UsernameNotFoundException("Email không hợp lệ!.");

        Set<GrantedAuthority> authorities = new HashSet<>();
        String roleName = customer.get().getRole().getRoleName();
        authorities.add(new SimpleGrantedAuthority(roleName));

        return new UserDetailsDto(customer.get().getEmail(), customer.get().getPassword(), authorities);
    }

}
