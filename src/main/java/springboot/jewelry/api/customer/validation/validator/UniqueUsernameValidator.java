package springboot.jewelry.api.customer.validation.validator;


import org.springframework.beans.factory.annotation.Autowired;
import springboot.jewelry.api.customer.service.CustomerService;
import springboot.jewelry.api.customer.validation.anotation.UniqueUsername;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired
    private CustomerService service;

    private String message;

    @Override
    public void initialize(UniqueUsername constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        boolean isTakenUsername = service.isTakenUsername(username);

        if (!isTakenUsername) {
            return true;
        }
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }
}
