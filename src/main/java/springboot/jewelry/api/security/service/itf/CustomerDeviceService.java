package springboot.jewelry.api.security.service.itf;

import springboot.jewelry.api.security.dto.DeviceInfoDto;
import springboot.jewelry.api.security.model.CustomerDevice;
import springboot.jewelry.api.security.model.RefreshToken;

import java.util.Optional;

public interface CustomerDeviceService {
    Optional<CustomerDevice> findByCustomerId(Long customerId);

    Optional<CustomerDevice> findByRefreshToken(RefreshToken refreshToken);

    CustomerDevice createUserDevice(DeviceInfoDto deviceInfoDto);

    void verifyRefreshAvailability(RefreshToken refreshToken);
}
