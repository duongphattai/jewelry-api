package springboot.jewelry.api.product.validation.annotation;

import springboot.jewelry.api.product.validation.validator.FormatPercentageGoldValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = FormatPercentageGoldValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FormatPercentageGold {

    String message() default "{product.validation.annotation.format-percentage-gold.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
