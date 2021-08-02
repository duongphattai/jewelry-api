package springboot.jewelry.api.product.validation.validator;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.jewelry.api.product.validation.annotation.ExistsSupplierCode;
import springboot.jewelry.api.supplier.model.Supplier;
import springboot.jewelry.api.supplier.repository.SupplierRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class ExistsSupplierCodeValidator implements ConstraintValidator<ExistsSupplierCode, String> {

    @Autowired
    private SupplierRepository supplierRepository;

    private String message;

    @Override
    public void initialize(ExistsSupplierCode constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String supplierCode, ConstraintValidatorContext context) {
        Optional<Supplier> supplierOpt = supplierRepository.findByCode(supplierCode);
        if(supplierOpt.isPresent()){
            return true;
        }

        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }
}
