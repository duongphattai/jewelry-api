package springboot.jewelry.api.product.validation.validator;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.jewelry.api.product.repository.GoldTypeRepository;
import springboot.jewelry.api.product.validation.annotation.UniqueGoldType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueGoldTypeValidator implements ConstraintValidator<UniqueGoldType, Double> {

    @Autowired
    private GoldTypeRepository goldTypeRepository;

    private String message;

    @Override
    public void initialize(UniqueGoldType constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Double percentage, ConstraintValidatorContext context) {
        boolean isTakenPercentage = goldTypeRepository.countByPercentage(percentage) >= 1;
        if(!isTakenPercentage){
            return true;
        }
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }
}
