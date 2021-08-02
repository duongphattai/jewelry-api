package springboot.jewelry.api.customer.validation.validator;


import org.springframework.beans.factory.annotation.Autowired;
import springboot.jewelry.api.customer.repository.CustomerRepository;
import springboot.jewelry.api.customer.validation.annotation.UniqueUsername;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired
    private CustomerRepository customerRepository;

    private String message;

    @Override
    public void initialize(UniqueUsername constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        boolean isTakenUsername = customerRepository.countByUsername(username) >= 1;
        if (!isTakenUsername) {
            return true;
        }
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }
}
