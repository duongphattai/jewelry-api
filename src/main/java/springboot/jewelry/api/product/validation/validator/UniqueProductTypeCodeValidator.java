package springboot.jewelry.api.product.validation.validator;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.jewelry.api.product.repository.ProductTypeRepository;
import springboot.jewelry.api.product.validation.annotation.UniqueProductTypeCode;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueProductTypeCodeValidator implements ConstraintValidator<UniqueProductTypeCode, String> {

    @Autowired
    private ProductTypeRepository productTypeRepository;

    String message;

    @Override
    public void initialize(UniqueProductTypeCode constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String productTypeCode, ConstraintValidatorContext context) {
        boolean isTakenProductTypeCode = productTypeRepository.countByCode(productTypeCode) >= 1;
        if (!isTakenProductTypeCode) {
            return true;
        }

        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }
}
