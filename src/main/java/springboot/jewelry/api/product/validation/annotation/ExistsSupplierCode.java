package springboot.jewelry.api.product.validation.annotation;

import springboot.jewelry.api.product.validation.validator.ExistsSupplierCodeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ExistsSupplierCodeValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsSupplierCode {

    String message() default "{product.validation.annotation.exists-supplier-code.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
