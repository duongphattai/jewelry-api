package springboot.jewelry.api.shopping.model;

import lombok.*;
import springboot.jewelry.api.commondata.model.AbstractEntity;
import springboot.jewelry.api.product.model.Product;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "jewelry_cart_items")
public class CartItem extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Cart cart;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @NonNull
    @Min(value = 1)
    private Integer quantity;

}
