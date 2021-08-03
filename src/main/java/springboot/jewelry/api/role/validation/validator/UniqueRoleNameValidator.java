package springboot.jewelry.api.role.validation.validator;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.jewelry.api.role.service.RoleService;
import springboot.jewelry.api.role.validation.annotation.UniqueRoleName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueRoleNameValidator implements ConstraintValidator<UniqueRoleName, String> {

    @Autowired
    private RoleService service;

    private String message;

    @Override
    public void initialize(UniqueRoleName constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String roleName, ConstraintValidatorContext context) {
        boolean isTakenRoleName = service.isTakenRoleName(roleName);

        if (!isTakenRoleName) {
            return true;
        }
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }
}
