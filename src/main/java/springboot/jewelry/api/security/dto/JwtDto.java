package springboot.jewelry.api.security.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtDto {

    private String jwt;

    public JwtDto jwt(String jwt) {
        this.jwt = jwt;
       return this;
    }
}
