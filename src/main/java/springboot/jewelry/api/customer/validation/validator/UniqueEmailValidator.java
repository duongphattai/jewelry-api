package springboot.jewelry.api.customer.validation.validator;


import org.springframework.beans.factory.annotation.Autowired;
import springboot.jewelry.api.customer.repository.CustomerRepository;
import springboot.jewelry.api.customer.validation.annotation.UniqueEmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private CustomerRepository customerRepository;

    private String message;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {

        boolean isTakenEmail = customerRepository.countByEmail(email) >= 1;
        if (!isTakenEmail) {
            return true;
        }

        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }
}
