package springboot.jewelry.api.security.model;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;


@Getter
@Setter
@Entity
@Table(name = "jewelry_refreshtoken")
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_device_id", unique = true)
    private CustomerDevice customerDevice;

    private Long refreshCount;

    @Column(nullable = false)
    private Instant expiryDate;

    public void incrementRefreshCount() {
        refreshCount = refreshCount + 1;
    }
}
