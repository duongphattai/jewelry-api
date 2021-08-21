package springboot.jewelry.api.security.jwt;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import springboot.jewelry.api.customer.model.Customer;
import springboot.jewelry.api.security.cache.LoggedOutJwtTokenCache;
import springboot.jewelry.api.security.event.OnCustomerLogoutSuccessEvent;
import springboot.jewelry.api.security.exception.InvalidTokenRequestException;
import springboot.jewelry.api.security.service.CustomerPrincipal;

import java.time.Instant;
import java.util.Date;

@Component
public class JwtProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Autowired
    private LoggedOutJwtTokenCache loggedOutJwtTokenCache;

    public String generateJwtToken(Authentication authentication) {

        CustomerPrincipal customerPrincipal = (CustomerPrincipal) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 3600000);

        return Jwts.builder()
                .setSubject((customerPrincipal.getUsername()))
                .setId(Long.toString(customerPrincipal.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, "HelloWorld")
                .compact();
    }

    public String generateTokenFromCustomer(Customer customer) {
        Instant expiryDate = Instant.now().plusMillis(3600000);
        return Jwts.builder()
                .setSubject(customer.getEmail())
                .setId(Long.toString(customer.getId()))
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(expiryDate))
                .signWith(SignatureAlgorithm.HS512, "HelloWorld")
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey("HelloWorld")
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public Date getTokenExpiryFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey("HelloWorld")
                .parseClaimsJws(token)
                .getBody();

        return claims.getExpiration();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey("HelloWorld").parseClaimsJws(authToken);
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
            String customerEmail = previouslyLoggedOutEvent.getCustomerEmail();
            Date logoutEventDate = previouslyLoggedOutEvent.getEventTime();
            String errorMessage = String.format("Token corresponds to an already logged out user [%s] at [%s]. Please login again", customerEmail, logoutEventDate);
            throw new InvalidTokenRequestException("JWT", authToken, errorMessage);
        }
    }

    public long getExpiryDuration() {
        return 3600000;
    }
}
