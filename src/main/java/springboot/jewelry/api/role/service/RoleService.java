package springboot.jewelry.api.role.service;

import springboot.jewelry.api.role.model.Role;
import springboot.jewelry.api.commondata.GenericService;
import springboot.jewelry.api.role.dto.RoleCreateDto;

import java.util.List;

public interface RoleService {

    boolean isTakenRoleName(String roleName);

    Role save(RoleCreateDto dto);

    List<Role> findAll();

    //Role changeRoleWithEmail(String email);
}
