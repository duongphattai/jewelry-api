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
import springboot.jewelry.api.role.model.Role;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CustomerRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Customer> customer = repository.findByUsername(username);
        if(!customer.isPresent())
            throw new UsernameNotFoundException("Username is invalid.");

        Set<GrantedAuthority> authorities = getAuthorities((Set<Role>) customer.get().getRole());

        return new UserDetailsDto(customer.get().getUsername(), customer.get().getPassword(), authorities);
    }

    private Set<GrantedAuthority> getAuthorities(Set<Role> role) {
        Set<GrantedAuthority> authorities = new HashSet<>();

        Iterator<Role> iterator = role.iterator();

        while(iterator.hasNext())
            authorities.add(new SimpleGrantedAuthority(iterator.next().getRoleName()));

        return authorities;
    }
}
