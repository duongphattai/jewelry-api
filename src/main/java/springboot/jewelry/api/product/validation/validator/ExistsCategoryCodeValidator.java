package springboot.jewelry.api.product.validation.validator;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.jewelry.api.product.model.Category;
import springboot.jewelry.api.product.repository.CategoryRepository;
import springboot.jewelry.api.product.validation.annotation.ExistsCategoryCode;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class ExistsCategoryCodeValidator implements ConstraintValidator<ExistsCategoryCode, String> {

    @Autowired
    private CategoryRepository productTypeRepository;

    private String message;

    @Override
    public void initialize(ExistsCategoryCode constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String productTypeCode, ConstraintValidatorContext context) {

        Optional<Category> productTypeOpt = productTypeRepository.findByCode(productTypeCode);
        if(productTypeOpt.isPresent()){
            return true;
        }
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }
}
