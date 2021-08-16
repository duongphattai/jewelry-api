package springboot.jewelry.api.supplier.validation.annotation;

import springboot.jewelry.api.supplier.validation.validator.UniqueSupplierCodeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueSupplierCodeValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueSupplierCode {

    String message() default "{supplier.validation.annotation.unique-supplier-code.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
