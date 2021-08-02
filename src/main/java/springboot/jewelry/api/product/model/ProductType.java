package springboot.jewelry.api.product.model;

import lombok.Getter;
import lombok.Setter;
import springboot.jewelry.api.commondata.model.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "jewelry_product_type")
public class ProductType extends AbstractEntity {

    @Column(unique = true)
    @NotBlank(message = "{ProductType.code.NotBlank}")
    @Size(min = 3, max = 10, message = "{ProductType.code.Size}")
    private String code;

    @NotBlank(message = "{ProductType.name.NotBlank}")
    @Size(min = 1, max = 30, message = "{ProductType.name.Size}")
    private String name;

    @OneToMany(mappedBy = "productType", cascade = CascadeType.ALL)
    private Set<Product> products = new HashSet<>();
}
