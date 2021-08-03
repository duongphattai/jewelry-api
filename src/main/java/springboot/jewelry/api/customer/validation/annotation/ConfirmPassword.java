package springboot.jewelry.api.customer.validation.annotation;

import springboot.jewelry.api.customer.validation.validator.ConfirmPasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ConfirmPasswordValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfirmPassword {

    String getPassword() default "getPassword";

    String getConfirmPassword() default "getConfirmPassword";

    String message() default "{customer.validation.annotation.ConfirmPassword.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
