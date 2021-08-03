package springboot.jewelry.api.customer.validation.validator;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.jewelry.api.customer.repository.CustomerRepository;
import springboot.jewelry.api.customer.validation.annotation.UniquePhoneNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniquePhoneNumberValidator implements ConstraintValidator<UniquePhoneNumber, String> {

    @Autowired
    private CustomerRepository customerRepository;

    private String message;

    @Override
    public void initialize(UniquePhoneNumber constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        boolean isTakenPhoneNumber = customerRepository.countByPhoneNumber(phoneNumber) >= 1;
        if (!isTakenPhoneNumber) {
            return true;
        }
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }
}
