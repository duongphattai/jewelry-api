package springboot.jewelry.api.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "jewelry_product_category")
public class Category extends AbstractEntity {

    @NotBlank(message = "{product-category.code.not-blank}")
    @Size(min = 3, max = 10, message = "{product-category.code.size}")
    @Column(unique = true)
    private String code;

    @NotBlank(message = "{product-category.name.not-blank}")
    @Size(min = 1, max = 30, message = "{product-category.name.size}")
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Set<Product> products = new HashSet<>();
}
