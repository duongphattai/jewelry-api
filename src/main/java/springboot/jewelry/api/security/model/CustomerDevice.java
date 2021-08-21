package springboot.jewelry.api.security.model;

import lombok.*;
import springboot.jewelry.api.customer.model.Customer;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "jewelry_customer_device")
public class CustomerDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
