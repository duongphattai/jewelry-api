package springboot.jewelry.api.customer.validation.anotation;

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
    public String getPassword() default "getPassword";

    public String getConfirmPassword() default "getConfirmPassword";

    public String message() default "Mật khẩu nhập lại không chính xác!";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}
