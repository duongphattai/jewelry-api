package springboot.jewelry.api.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import springboot.jewelry.api.commondata.model.ResponseHandler;
import springboot.jewelry.api.customer.model.Customer;
import springboot.jewelry.api.customer.service.CustomerService;
import springboot.jewelry.api.customer.validation.annotation.CurrentCustomer;
import springboot.jewelry.api.security.dto.LoginDto;
import springboot.jewelry.api.security.dto.LogoutDto;
import springboot.jewelry.api.security.dto.TokenRefreshRequestDto;
import springboot.jewelry.api.security.event.OnCustomerLogoutSuccessEvent;
import springboot.jewelry.api.security.exception.CustomerLogoutException;
import springboot.jewelry.api.security.exception.ResourceNotFoundException;
import springboot.jewelry.api.security.exception.TokenRefreshException;
import springboot.jewelry.api.security.jwt.JwtProvider;
import springboot.jewelry.api.security.model.CustomerDevice;
import springboot.jewelry.api.security.model.RefreshToken;
import springboot.jewelry.api.security.dto.JwtDto;
import springboot.jewelry.api.security.dto.CustomerPrincipalDto;
import springboot.jewelry.api.security.service.itf.CustomerDeviceService;
import springboot.jewelry.api.security.service.itf.RefreshTokenService;
import springboot.jewelry.api.util.MessageUtils;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private CustomerDeviceService customerDeviceService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;



    @PostMapping("/login")
    public ResponseEntity<Object> authenticateUser(@Valid @RequestBody LoginDto loginDto) {
            Customer customer = customerService.findByEmail(loginDto.getEmail())
                    .orElseThrow(() -> new ResourceNotFoundException("Email hoặc mật khẩu không chính xác!"));
            if (customer.getActive()) {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginDto.getEmail(),
                                loginDto.getPassword()
                        )
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String jwtToken = jwtProvider.generateJwtToken(authentication);
                customerDeviceService.findByCustomerId(customer.getId())
                        .map(CustomerDevice::getRefreshToken)
                        .map(RefreshToken::getId)
                        .ifPresent(refreshTokenService::deleteById);


                CustomerDevice customerDevice = customerDeviceService.createUserDevice(loginDto.getDeviceInfoDto());
                RefreshToken refreshToken = refreshTokenService.createRefreshToken();
                customerDevice.setCustomer(customer);
                customerDevice.setRefreshToken(refreshToken);
                refreshToken.setCustomerDevice(customerDevice);
                refreshToken = refreshTokenService.save(refreshToken);
                return ResponseHandler.getResponse(new JwtDto(jwtToken, refreshToken.getToken(),
                        jwtProvider.getJwtRefreshDuration()), HttpStatus.OK);
            }

        return ResponseHandler.getResponse(new MessageUtils("Tài khoản đã bị khóa!"), HttpStatus.OK);
    }

    @PostMapping("/token/refresh")
    public ResponseEntity<?> refreshJwtToken(@Valid @RequestBody TokenRefreshRequestDto dto) {
        String requestRefreshToken = dto.getRefreshToken();

        Optional<String> token = Optional.of(refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshToken -> {
                    refreshTokenService.verifyExpiration(refreshToken);
                    customerDeviceService.verifyRefreshAvailability(refreshToken);
                    refreshTokenService.increaseCount(refreshToken);
                    return refreshToken;
                })
                .map(RefreshToken::getCustomerDevice)
                .map(CustomerDevice::getCustomer)
                .map(c -> jwtProvider.generateTokenFromCustomer(c))
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken, "Vui lòng đăng nhập lại!")));
        return ResponseHandler.getResponse(new JwtDto(token.get(), dto.getRefreshToken(),
                jwtProvider.getJwtRefreshDuration()), HttpStatus.OK);
    }

    @PutMapping("/logout")
    public ResponseEntity<Object> logoutCustomer(@CurrentCustomer CustomerPrincipalDto currentCustomer,
                                                 @Valid @RequestBody LogoutDto logoutDto){
        String deviceId = logoutDto.getDeviceInfo().getDeviceId();
        CustomerDevice customerDevice = customerDeviceService.findByCustomerId(currentCustomer.getId())
                .filter(device -> device.getDeviceId().equals(deviceId))
                .orElseThrow(() -> new CustomerLogoutException(
                        logoutDto.getDeviceInfo().getDeviceId(), "Thiết bị không hợp lệ với tài khoản!"));
        refreshTokenService.deleteById(customerDevice.getRefreshToken().getId());

        OnCustomerLogoutSuccessEvent logoutSuccessEvent =
                new OnCustomerLogoutSuccessEvent(currentCustomer.getEmail(), logoutDto.getToken(), logoutDto);
        applicationEventPublisher.publishEvent(logoutSuccessEvent);
        return ResponseHandler.getResponse(new MessageUtils("Đăng xuất thành công!"), HttpStatus.OK);
    }
}
