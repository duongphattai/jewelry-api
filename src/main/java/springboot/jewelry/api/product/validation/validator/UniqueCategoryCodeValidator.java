package springboot.jewelry.api.product.validation.validator;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.jewelry.api.product.repository.CategoryRepository;
import springboot.jewelry.api.product.validation.annotation.UniqueCategoryCode;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueCategoryCodeValidator implements ConstraintValidator<UniqueCategoryCode, String> {

    @Autowired
    private CategoryRepository productTypeRepository;

    String message;

    @Override
    public void initialize(UniqueCategoryCode constraintAnnotation) {
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
