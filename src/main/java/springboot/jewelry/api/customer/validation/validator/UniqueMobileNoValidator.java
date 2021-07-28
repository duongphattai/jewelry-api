package springboot.jewelry.api.customer.validation.validator;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.jewelry.api.customer.service.CustomerService;
import springboot.jewelry.api.customer.validation.anotation.UniqueMobileNo;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueMobileNoValidator implements ConstraintValidator<UniqueMobileNo, String> {

    @Autowired
    private CustomerService service;

    private String message;

    @Override
    public void initialize(UniqueMobileNo constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String MobileNo, ConstraintValidatorContext context) {
        boolean isTakenMobileNo = service.isTakenMobileNo(MobileNo);

        if (!isTakenMobileNo) {
            return true;
        }
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }
}
