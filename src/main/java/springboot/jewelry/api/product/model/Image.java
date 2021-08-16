package springboot.jewelry.api.product.model;

import lombok.*;
import springboot.jewelry.api.commondata.model.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "jewelry_product_image")
public class Image extends AbstractEntity {

    @NonNull
    @NotBlank(message = "{product-image.g-drive-id.not-blank}")
    private String gDriveId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}
