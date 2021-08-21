package springboot.jewelry.api.security.model;

import lombok.*;
import springboot.jewelry.api.commondata.model.AbstractEntity;
import springboot.jewelry.api.customer.model.Customer;

import javax.persistence.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jewelry_customer_device")
public class CustomerDevice {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_device_seq")
    @SequenceGenerator(name = "customer_device_seq", allocationSize = 1)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    private String deviceType;

    @Column(nullable = false)
    private String deviceId;

    @OneToOne(optional = false, mappedBy = "customerDevice")
    private RefreshToken refreshToken;

    private Boolean isRefreshActive;
}
