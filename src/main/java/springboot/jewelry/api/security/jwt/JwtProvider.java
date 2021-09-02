package springboot.jewelry.api.security.jwt;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import springboot.jewelry.api.customer.model.Customer;
import springboot.jewelry.api.security.cache.LoggedOutJwtTokenCache;
import springboot.jewelry.api.security.dto.CustomerPrincipalDto;
import springboot.jewelry.api.security.event.OnCustomerLogoutSuccessEvent;
import springboot.jewelry.api.security.exception.InvalidTokenRequestException;

import java.time.Instant;
import java.util.Date;

@Component
public class JwtProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jewelry.app.jwt-secret}")
    private String jwtSecret;

    @Value("${jewelry.app.jwt-duration}")
    private Long jwtDuration;

    @Value("${jewelry.app.jwt-refresh-duration}")
    private Long jwtRefreshDuration;

    public Long getJwtRefreshDuration() {
        return jwtRefreshDuration;
    }

    @Autowired
    private LoggedOutJwtTokenCache loggedOutJwtTokenCache;

    public String generateJwtToken(Authentication authentication) {

        CustomerPrincipalDto customerPrincipalDto = (CustomerPrincipalDto) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtDuration);
        System.out.println("fullName:" + customerPrincipalDto.getFullName());
        return Jwts.builder()
                .setSubject((customerPrincipalDto.getEmail()))
                .setId(Long.toString(customerPrincipalDto.getId()))
                .claim("fullName", customerPrincipalDto.getFullName())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String generateTokenFromCustomer(Customer customer) {
        Instant expiryDate = Instant.now().plusMillis(jwtDuration);
        return Jwts.builder()
                .setSubject(customer.getEmail())
                .setId(Long.toString(customer.getId()))
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(expiryDate))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public Date getTokenExpiryFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getExpiration();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            validateTokenIsNotForALoggedOutDevice(authToken);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message: {}", e);
        }

        return false;
    }

    private void validateTokenIsNotForALoggedOutDevice(String authToken) {
        OnCustomerLogoutSuccessEvent previouslyLoggedOutEvent = loggedOutJwtTokenCache.getLogoutEventForToken(authToken);
        if (previouslyLoggedOutEvent != null) {
            String errorMessage = String.format("Đã xảy ra lỗi. Vui lòng thử lại!");
            throw new InvalidTokenRequestException("JWT", authToken, errorMessage);
        }
    }

}
