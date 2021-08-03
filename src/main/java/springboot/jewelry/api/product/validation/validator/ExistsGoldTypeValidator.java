package springboot.jewelry.api.product.validation.validator;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.jewelry.api.product.model.GoldType;
import springboot.jewelry.api.product.repository.GoldTypeRepository;
import springboot.jewelry.api.product.validation.annotation.ExistsGoldType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class ExistsGoldTypeValidator implements ConstraintValidator<ExistsGoldType, Double> {

    @Autowired
    private GoldTypeRepository goldTypeRepository;

    private String message;

    @Override
    public void initialize(ExistsGoldType constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Double percentage, ConstraintValidatorContext context) {
        Optional<GoldType> goldTypeOpt = goldTypeRepository.findByPercentage(percentage);
        if(goldTypeOpt.isPresent()){
            return true;
        }

        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }
}
