package springboot.jewelry.api.product.validation.validator;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.jewelry.api.product.model.ProductType;
import springboot.jewelry.api.product.repository.ProductTypeRepository;
import springboot.jewelry.api.product.validation.annotation.ExistsProductTypeCode;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class ExistsProductTypeCodeValidator implements ConstraintValidator<ExistsProductTypeCode, String> {

    @Autowired
    private ProductTypeRepository productTypeRepository;

    private String message;

    @Override
    public void initialize(ExistsProductTypeCode constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String productTypeCode, ConstraintValidatorContext context) {

        Optional<ProductType> productTypeOpt = productTypeRepository.findByCode(productTypeCode);
        if(productTypeOpt.isPresent()){
            return true;
        }
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }
}
