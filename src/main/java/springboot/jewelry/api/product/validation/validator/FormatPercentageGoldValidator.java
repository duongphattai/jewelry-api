package springboot.jewelry.api.product.validation.validator;

import springboot.jewelry.api.product.validation.annotation.FormatPercentageGold;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FormatPercentageGoldValidator implements ConstraintValidator<FormatPercentageGold, Double> {

    private String message;

    @Override
    public void initialize(FormatPercentageGold constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Double percentageGold, ConstraintValidatorContext context) {

        if(percentageGold < 61 || percentageGold > 99.99){
            return false;
        }

        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return true;
    }
}
