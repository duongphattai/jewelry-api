package springboot.jewelry.api.product.model;

import lombok.Getter;
import lombok.Setter;

import springboot.jewelry.api.commondata.model.AbstractEntity;
import springboot.jewelry.api.product.validation.annotation.FormatPercentageGold;

import javax.persistence.*;
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
    @FormatPercentageGold
    private Double percentage;

    @OneToMany(mappedBy = "goldType", cascade = CascadeType.ALL)
    private Set<Product> products = new HashSet<>();
}
