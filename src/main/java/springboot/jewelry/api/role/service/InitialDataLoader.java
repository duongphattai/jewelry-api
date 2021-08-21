package springboot.jewelry.api.role.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springboot.jewelry.api.role.model.Role;
import springboot.jewelry.api.role.model.RoleName;
import springboot.jewelry.api.role.repository.RoleRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class InitialDataLoader {

    @Autowired
    private RoleRepository roleRepository;

    @Bean
    public ApplicationRunner initializer() {
        List<RoleName> roles = Arrays.asList(RoleName.ROLE_ADMIN, RoleName.ROLE_USER);
        return args -> roles.forEach(i -> createRoleIfNotFound(i));
    }

    private Optional<Role> createRoleIfNotFound(RoleName roleName) {
        Optional<Role> role = roleRepository.findByRoleName(roleName);
        if (!role.isPresent()) {
            Role newRole = new Role();
            newRole.setRoleName(roleName);
            newRole = roleRepository.save(newRole);
        }
        return role;
    }

}
