package springboot.jewelry.api.role.service;

import springboot.jewelry.api.role.model.Role;
import springboot.jewelry.api.commondata.GenericService;
import springboot.jewelry.api.role.dto.RoleCreateDto;

public interface RoleService extends GenericService<Role, Long> {

    boolean isTakenRoleName(String roleName);

    Role save(RoleCreateDto dto);

    Role updateRoleInfo(RoleCreateDto dto, Long id);

    Role changeRoleForCustomer(String username, Long roleId);
}
