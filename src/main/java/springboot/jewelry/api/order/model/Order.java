package springboot.jewelry.api.order.model;

import lombok.Getter;
import lombok.Setter;
import springboot.jewelry.api.commondata.model.AbstractEntity;
import springboot.jewelry.api.customer.model.Customer;
import springboot.jewelry.api.order.util.OrderStatus;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "jewelry_order")
public class Order extends AbstractEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @NotNull
    private Double total;

    @NotNull
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @NotBlank(message = "{order.full-name.not-blank}")
    @Size(min = 3, max = 50, message = "{order.full-name.size}")
    private String fullName;

    @NotBlank(message = "{order.address.not-blank}")
    @Size(min = 20, max = 100, message = "{order.address.size}")
    private String address;

    @NotBlank(message = "{order.phone-number.not-blank}")
    @Pattern(regexp = "(^((?=(0))[0-9]{10})$)", message = "{order.phone-number.pattern}")
    private String phoneNumber;

    @NotBlank(message = "{order.email.not-blank}")
    @Email(message = "{order.email.format}")
    private String email;

    @Size(max = 500, message = "{order.description.size}")
    private String content;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderItem> items = new HashSet<>();

    public void addItem(OrderItem item) {
        this.items.add(item);
        item.setOrder(this);
    }
}
