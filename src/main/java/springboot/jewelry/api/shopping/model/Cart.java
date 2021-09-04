package springboot.jewelry.api.shopping.model;

import lombok.*;
import springboot.jewelry.api.commondata.model.AbstractEntity;
import springboot.jewelry.api.customer.model.Customer;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "jewelry_cart")
public class Cart extends AbstractEntity {

    @OneToOne(fetch = FetchType.LAZY)
    private Customer customer;

    private Double total = 0.0;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartItem> items = new HashSet<>();

    public void addItem(CartItem item) {
        this.items.add(item);
        item.setCart(this);
    }

    public void removeItem(CartItem item) {
        this.items.remove(item);
        item.setCart(null);
    }


}
