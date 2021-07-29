package springboot.jewelry.api.customer.validation.validator;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.jewelry.api.customer.service.CustomerService;
import springboot.jewelry.api.customer.validation.anotation.UniqueEmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private CustomerService service;

    private String message;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        boolean isTakenMobileNo = service.isTakenEmail(email);

        if (!isTakenMobileNo) {
            return true;
        }
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }
}
