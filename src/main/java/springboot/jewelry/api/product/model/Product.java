package springboot.jewelry.api.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import springboot.jewelry.api.commondata.model.AbstractEntity;
import springboot.jewelry.api.supplier.model.Supplier;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@Entity
@Table(name = "jewelry_product")
public class Product extends AbstractEntity {

    @NotBlank(message = "{Product.sku.NotBlank}")
    @Size(min = 5, max = 5, message = "{Product.sku.Size}")
    @Column(unique = true)
    private String sku;

    @NotBlank(message = "{Product.name.NotBlank}")
    @Size(min = 5, max = 100, message = "{Product.name.Size}")
    private String name;

    @Size(max = 500, message = "{Product.description.Size}")
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
