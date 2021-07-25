package springboot.jewelry.role.service;

import springboot.jewelry.commondata.GenericService;
import springboot.jewelry.role.dto.RoleCreateDto;
import springboot.jewelry.role.model.Role;

public interface RoleService extends GenericService<Role, Long> {

    boolean isTakenRoleName(String roleName);

    Role save(RoleCreateDto dto);

    Role updateRole(RoleCreateDto dto, Long id);

    Role addCustomer(String username, Long roleId);
}
