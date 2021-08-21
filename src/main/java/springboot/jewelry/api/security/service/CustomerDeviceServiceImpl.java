package springboot.jewelry.api.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.jewelry.api.security.dto.DeviceInfoDto;
import springboot.jewelry.api.security.exception.TokenRefreshException;
import springboot.jewelry.api.security.model.CustomerDevice;
import springboot.jewelry.api.security.model.RefreshToken;
import springboot.jewelry.api.security.repository.CustomerDeviceRepository;
import springboot.jewelry.api.security.service.itf.CustomerDeviceService;

import java.util.Optional;

@Service
public class CustomerDeviceServiceImpl implements CustomerDeviceService {

    @Autowired
    private CustomerDeviceRepository customerDeviceRepository;

    @Override
    public Optional<CustomerDevice> findByCustomerId(Long customerId) {
        return customerDeviceRepository.findByCustomerId(customerId);
    }

    @Override
    public Optional<CustomerDevice> findByRefreshToken(RefreshToken refreshToken) {
        return customerDeviceRepository.findByRefreshToken(refreshToken);
    }

    @Override
    public CustomerDevice createUserDevice(DeviceInfoDto deviceInfoDto) {
        CustomerDevice customerDevice = new CustomerDevice();
        customerDevice.setDeviceId(deviceInfoDto.getDeviceId());
        customerDevice.setDeviceType(deviceInfoDto.getDeviceType());
        customerDevice.setIsRefreshActive(true);
        return customerDevice;
    }

    @Override
    public void verifyRefreshAvailability(RefreshToken refreshToken) {
        CustomerDevice customerDevice = findByRefreshToken(refreshToken)
                .orElseThrow(() -> new TokenRefreshException(refreshToken.getToken(),
                        "No device found for the matching token. Please login again"));

        if (!customerDevice.getIsRefreshActive()) {
            throw new TokenRefreshException(refreshToken.getToken(),
                    "Refresh blocked for the device. Please login through a different device");
        }
    }
}
