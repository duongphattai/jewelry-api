package springboot.jewelry.api.supplier.model;

import lombok.Getter;
import lombok.Setter;
import springboot.jewelry.api.commondata.model.AbstractEntity;
import springboot.jewelry.api.product.model.Product;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "jewelry_supplier")
public class Supplier extends AbstractEntity {

    @Column(unique = true)
    private String code;

    @Column(unique = true)
    private String name;

    private String phoneNumber;

    private String email;

    private String address;

    private String logo;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    private Set<Product> products = new HashSet<>();
}
