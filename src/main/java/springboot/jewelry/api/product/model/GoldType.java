package springboot.jewelry.api.product.model;

import lombok.Getter;
import lombok.Setter;
import springboot.jewelry.api.commondata.model.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "jewelry_product_gold_type")
public class GoldType extends AbstractEntity {

    @Column(unique = true)
    @NotNull
    @Min(value = 61, message = "{product-gold-type.percentage.min}")
    @Max(value = 99, message = "{product-gold-type.percentage.max}")
    private Double percentage;

    @OneToMany(mappedBy = "goldType", cascade = CascadeType.ALL)
    private Set<Product> products = new HashSet<>();
}
