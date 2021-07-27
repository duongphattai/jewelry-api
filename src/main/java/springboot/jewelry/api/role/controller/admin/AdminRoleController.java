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
    private RoleService service;

    @GetMapping("")
    public ResponseEntity<Object> findAll(){
        List<Role> roles = service.findAll();
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

        Role role = service.save(dto);

        return ResponseHandler.getResponse(role, HttpStatus.OK);
    }

    @PutMapping("/{role-id}")
    public ResponseEntity<Object> updateRole(@PathVariable("role-id") Long id ,
                                             @Valid @RequestBody RoleCreateDto dto ,
                                             BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return ResponseHandler.getResponse(HttpStatus.BAD_REQUEST);
        }

        Role role = service.updateRole(dto, id);

        return ResponseHandler.getResponse(role, HttpStatus.OK);
    }

    @PutMapping("/{role-id}/{username}")
    public ResponseEntity<Object> addCustomerToRole(@PathVariable("role-id") Long roleId ,
                                                    @PathVariable("username") String username){
        Role role = service.addCustomerToRole(username, roleId);

        return ResponseHandler.getResponse(role, HttpStatus.OK);

    }

    @DeleteMapping("/{role-id}")
    public ResponseEntity<Object> deleteRole(@PathVariable("role-id") Long id){

        service.deleteById(id);

        return ResponseHandler.getResponse(HttpStatus.OK);
    }

}
