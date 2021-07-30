package springboot.jewelry.api.product.model;

import lombok.Getter;
import lombok.Setter;
import springboot.jewelry.api.commondata.model.AbstractEntity;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "jewelry_image")
public class Image extends AbstractEntity {

//    @Column(unique = true)
//    private String name;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_id")
//    private Product product;
}
