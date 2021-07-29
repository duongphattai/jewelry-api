package springboot.jewelry.api.product.model;

import springboot.jewelry.api.commondata.model.AbstractEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "jewelry_product_type")
public class ProductType extends AbstractEntity {

    @OneToMany(mappedBy = "productType", cascade = CascadeType.ALL)
    private Set<Product> products = new HashSet<>();
}
