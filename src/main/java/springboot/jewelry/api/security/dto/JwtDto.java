package springboot.jewelry.api.security.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtDto {

    private String jwt;

    private String refreshToken;

    public JwtDto jwt(String jwt) {
        this.jwt = jwt;
       return this;
    }

    public JwtDto refreshToken(String refreshToken){
        this.refreshToken = refreshToken;
        return this;
    }

}
