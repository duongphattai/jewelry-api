package springboot.jewelry.api.customer.validation.anotation;

import springboot.jewelry.api.customer.validation.validator.UniqueEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Constraint(validatedBy = UniqueEmailValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {

    String message() default "Email đã được sử dụng!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
