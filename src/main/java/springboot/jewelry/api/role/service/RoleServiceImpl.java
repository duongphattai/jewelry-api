package springboot.jewelry.api.role.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import springboot.jewelry.api.customer.repository.CustomerRepository;
import springboot.jewelry.api.role.model.Role;
import springboot.jewelry.api.role.model.RoleName;
import springboot.jewelry.api.role.repository.RoleRepository;
import springboot.jewelry.api.commondata.GenericServiceImpl;
import springboot.jewelry.api.customer.model.Customer;
import springboot.jewelry.api.role.dto.RoleCreateDto;
import springboot.jewelry.api.util.MapDtoToModel;

import java.util.*;

@AllArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

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
        //role = mapper.map(dto, role);
        return roleRepository.save(role);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

//    @Override
//    public Role updateRoleInfo(RoleCreateDto dto, Long id) {
//        Role role = roleRepository.getOne(id);
//        role = mapper.map(dto, role);
//        return roleRepository.save(role);
//    }

//    @Override
//    public Role changeRoleWithEmail(String email) {
//
//        Optional<Customer> customer = customerRepository.findByEmail(email);
//
//        Set<RoleName> strRoles = new HashSet<>(EnumSet.allOf(RoleName.class));
//        Set<Role> roles = new HashSet<>();
//
//        strRoles.forEach(role1 -> {
//            Optional<Role> adminRole = roleRepository.findByRoleName(RoleName.ROLE_ADMIN);
//            if (customer.isPresent()) {
//                //role.getCustomers().add(customer.get());
//                roles.add(adminRole.get());
//            }
//        });
//
//        return customerRepository.save(customer);
//    }
}
