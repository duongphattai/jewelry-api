package springboot.jewelry.api.role.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.jewelry.api.role.model.Role;
import springboot.jewelry.api.role.model.RoleName;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    int countByRoleName(String roleName);

    Optional<Role> findByRoleName(RoleName roleName);
}
