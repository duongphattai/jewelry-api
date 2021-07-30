package springboot.jewelry.api.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import springboot.jewelry.api.commondata.model.AbstractEntity;
import springboot.jewelry.api.supplier.model.Supplier;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "jewelry_product")
public class Product extends AbstractEntity {

    private String sku;
    private String name;
    private String description;

    private Double goldWeight;

    private Double costPrice;
    private Double price;
    private Integer quantity;
    private Double totalCostPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    @NotNull
    @JsonIgnore
    private Supplier supplier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_type_id")
    @NotNull
    @JsonIgnore
    private ProductType productType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gold_type_id")
    @NotNull
    @JsonIgnore
    private GoldType goldType;

//    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
//    private Set<Image> images = new HashSet<>();

}
