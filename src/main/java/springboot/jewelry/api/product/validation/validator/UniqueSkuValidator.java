package springboot.jewelry.api.product.validation.validator;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.jewelry.api.product.repository.ProductRepository;
import springboot.jewelry.api.product.validation.annotation.UniqueSku;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueSkuValidator implements ConstraintValidator<UniqueSku, String> {

    @Autowired
    private ProductRepository productRepository;

    private String message;

    @Override
    public void initialize(UniqueSku constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String sku, ConstraintValidatorContext context) {
        boolean isTakenSku = productRepository.countBySku(sku) >= 1;
        if(!isTakenSku){
            return true;
        }

        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }
}
