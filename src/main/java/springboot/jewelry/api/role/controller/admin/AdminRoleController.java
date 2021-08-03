package springboot.jewelry.api.role.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springboot.jewelry.api.role.model.Role;
import springboot.jewelry.api.commondata.model.ResponseHandler;
import springboot.jewelry.api.role.dto.RoleCreateDto;
import springboot.jewelry.api.role.service.RoleService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin/role")
public class AdminRoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("")
    public ResponseEntity<Object> findAll(){
        List<Role> roles = roleService.findAll();
        if(roles.isEmpty()){
            return ResponseHandler.getResponse("Danh sách trống!", HttpStatus.OK);
        }

        return ResponseHandler.getResponse(roles, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Object> addRole(@Valid @RequestBody RoleCreateDto dto,
                                          BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseHandler.getResponse(bindingResult, HttpStatus.BAD_REQUEST);
        }

        Role role = roleService.save(dto);

        return ResponseHandler.getResponse(role, HttpStatus.OK);
    }

    @PutMapping("/{role-id}/{username}")
    public ResponseEntity<Object> changeRoleWithCustomer(@PathVariable("role-id") Long roleId ,
                                                    @PathVariable("username") String username){
        Role role = roleService.changeRoleForCustomer(username, roleId);

        return ResponseHandler.getResponse(role, HttpStatus.OK);

    }
}
