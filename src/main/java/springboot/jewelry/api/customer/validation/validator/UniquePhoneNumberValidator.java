package springboot.jewelry.api.customer.validation.validator;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.jewelry.api.customer.service.CustomerService;
import springboot.jewelry.api.customer.validation.anotation.UniquePhoneNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniquePhoneNumberValidator implements ConstraintValidator<UniquePhoneNumber, String> {

    @Autowired
    private CustomerService service;

    private String message;

    @Override
    public void initialize(UniquePhoneNumber constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        boolean isTakenPhoneNumber = service.isTakenPhoneNumber(phoneNumber);

        if (!isTakenPhoneNumber) {
            return true;
        }
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }
}
