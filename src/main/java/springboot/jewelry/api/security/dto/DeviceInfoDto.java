package springboot.jewelry.api.security.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class DeviceInfoDto {

    @NotBlank(message = "Device id cannot be blank")
    private String deviceId;

    @NotNull(message = "Device type cannot be null")
    private String deviceType;
}
