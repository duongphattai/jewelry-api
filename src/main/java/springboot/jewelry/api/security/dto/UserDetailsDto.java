package springboot.jewelry.api.security.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetailsDto extends User implements UserDetails {

    public UserDetailsDto(String email, String password, Collection<? extends GrantedAuthority> authorities){
        super(email, password, authorities);
    }
}
