package springboot.jewelry.api.product.model;

import lombok.Getter;
import lombok.Setter;
import springboot.jewelry.api.commondata.model.AbstractEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "jewelry_product_type")
public class ProductType extends AbstractEntity {

    @Column(unique = true)
    private String code;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "productType", cascade = CascadeType.ALL)
    private Set<Product> products = new HashSet<>();
}
