package springboot.jewelry.api.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springboot.jewelry.api.commondata.model.ResponseHandler;
import springboot.jewelry.api.security.dto.JwtDto;
import springboot.jewelry.api.security.dto.LoginDto;
import springboot.jewelry.api.security.jwt.JwtUtils;

import javax.validation.Valid;

@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginDto dto,
                                        BindingResult errors){
        Authentication authentication = null;

        try {
            // authenticate
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getUsername(),dto.getPassword()));

            // set authentication into SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwtToken = jwtUtils.generateJwtToken(authentication);
            return ResponseHandler.getResponse(new JwtDto().jwt(jwtToken), HttpStatus.OK);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        return ResponseHandler.getResponse("Username or password is invalid.", HttpStatus.BAD_REQUEST);
    }
}
