package springboot.jewelry.api.shopping.validation.validator;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import springboot.jewelry.api.product.model.Product;
import springboot.jewelry.api.product.repository.ProductRepository;
import springboot.jewelry.api.shopping.validation.annotation.ConfirmItemCreateOrUpdate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

@RequiredArgsConstructor
public class ConfirmItemCreateOrUpdateValidator implements ConstraintValidator<ConfirmItemCreateOrUpdate, Object> {

    private String messageProductSku;

    private String messageQuantity;

    private String getProductSku;

    private String getQuantity;

    @NonNull
    private ProductRepository productRepository;

    @Override
    public void initialize(ConfirmItemCreateOrUpdate constraintAnnotation) {
        this.messageProductSku = constraintAnnotation.messageProductSku();
        this.messageQuantity = constraintAnnotation.messageQuantity();
        this.getProductSku = constraintAnnotation.getProductSku();
        this.getQuantity = constraintAnnotation.getQuantity();
    }

    @SneakyThrows
    @Override
    public boolean isValid(Object item, ConstraintValidatorContext context) {
        String productSku = (String)item.getClass().getMethod(getProductSku).invoke(item);
        Integer quantity = (Integer)item.getClass().getMethod(getQuantity).invoke(item);

        Optional<Product> product = productRepository.findBySku(productSku);

        if(!product.isPresent()) {
            createConstraintViolation(context, messageProductSku);
            return false;
        }

        if(product.get().getQuantity() < quantity) {
            createConstraintViolation(context, messageQuantity);
            return false;
        }

        return true;
    }

    public void createConstraintViolation(ConstraintValidatorContext context, String message) {
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
    }
}
