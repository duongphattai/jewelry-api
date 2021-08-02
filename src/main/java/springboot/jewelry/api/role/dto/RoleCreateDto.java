package springboot.jewelry.api.role.dto;

import lombok.Getter;
import springboot.jewelry.api.role.validation.annotation.UniqueRoleName;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class RoleCreateDto {

    @NotBlank(message = "{role.name.not-blank}")
    @Size(min = 4, max = 50, message = "{role.name.size}")
    @UniqueRoleName
    private String roleName;

    @NotBlank(message = "{role.description.not-blank}")
    private String description;
}
