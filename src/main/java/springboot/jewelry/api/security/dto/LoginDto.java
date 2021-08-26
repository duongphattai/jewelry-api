package springboot.jewelry.api.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @Valid
    @NotNull(message = "Device info cannot be null")
    private DeviceInfoDto deviceInfoDto;
}
