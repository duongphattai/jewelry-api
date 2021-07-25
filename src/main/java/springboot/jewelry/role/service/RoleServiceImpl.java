package springboot.jewelry.role.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.jewelry.commondata.GenericServiceImpl;
import springboot.jewelry.customer.model.Customer;
import springboot.jewelry.customer.repository.CustomerRepository;
import springboot.jewelry.role.dto.RoleCreateDto;
import springboot.jewelry.role.model.Role;
import springboot.jewelry.role.repository.RoleRepository;
import springboot.jewelry.util.MapDtoToModel;

import java.util.Optional;

@AllArgsConstructor
@Service
public class RoleServiceImpl extends GenericServiceImpl<Role, Long> implements RoleService {

    private RoleRepository roleRepository;
    private MapDtoToModel<Object, Role> mapper;
    private CustomerRepository customerRepository;

    @Override
    public boolean isTakenRoleName(String roleName) {
        return roleRepository.countByRoleName(roleName) >= 1;
    }

    @Override
    public Role save(RoleCreateDto dto) {
        Role role = new Role();
        role = mapper.map(dto, role);
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(RoleCreateDto dto, Long id) {
        Role role = roleRepository.getOne(id);
        role = mapper.map(dto, role);
        return roleRepository.save(role);
    }

    @Override
    public Role addCustomer(String username, Long roleId) {
        Role role = roleRepository.getOne(roleId);
        Optional<Customer> customer = customerRepository.findByUsername(username);

        if (customer.isPresent()) {
            role.getCustomers().add(customer.get());
            customer.get().setRole(role);
        }
        return roleRepository.save(role);
    }
}
