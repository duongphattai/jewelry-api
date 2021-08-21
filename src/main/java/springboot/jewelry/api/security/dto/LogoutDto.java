package springboot.jewelry.api.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogoutDto {

    @Valid
    @NotNull(message = "Device info cannot be null")
    private DeviceInfoDto deviceInfo;

    @Valid
    @NotNull(message = "Existing Token needs to be passed")
    private String token;
}
