package springboot.jewelry.api.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogoutDto {

    @NotNull(message = "{logout-dto.device-info.not-null}")
    private DeviceInfoDto deviceInfo;

    @NotNull(message = "{logout-dto.token.not-null}")
    private String token;
}
