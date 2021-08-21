package springboot.jewelry.api.security.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class DeviceInfoDto {

    @NotBlank(message = "{device-info-dto.device-id.not-blank}")
    private String deviceId;

    @NotNull(message = "{device-info-dto.device-type.not-null}")
    private String deviceType;
}
