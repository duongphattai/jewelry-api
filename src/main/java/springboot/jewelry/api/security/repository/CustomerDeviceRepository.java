package springboot.jewelry.api.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.jewelry.api.security.model.CustomerDevice;
import springboot.jewelry.api.security.model.RefreshToken;

import java.util.Optional;

public interface CustomerDeviceRepository extends JpaRepository<CustomerDevice, Long> {

    @Override
    Optional<CustomerDevice> findById(Long id);

    Optional<CustomerDevice> findByRefreshToken(RefreshToken refreshToken);

    Optional<CustomerDevice> findByCustomerId(Long customerId);
}
