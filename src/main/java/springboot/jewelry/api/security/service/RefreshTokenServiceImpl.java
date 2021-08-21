package springboot.jewelry.api.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.jewelry.api.security.exception.TokenRefreshException;
import springboot.jewelry.api.security.model.RefreshToken;
import springboot.jewelry.api.security.repository.RefreshTokenRepository;
import springboot.jewelry.api.security.service.itf.RefreshTokenService;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    @Override
    public RefreshToken save(RefreshToken refreshToken) {
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public RefreshToken createRefreshToken() {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setExpiryDate(Instant.now().plusMillis(3600000));
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setRefreshCount(0L);
        return refreshToken;
    }

    @Override
    public void verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            throw new TokenRefreshException(token.getToken(), "Expired token. Please issue a new request");
        }
    }

    @Override
    public void deleteById(Long id) {
        refreshTokenRepository.deleteById(id);
    }

    @Override
    public void increaseCount(RefreshToken refreshToken) {
        refreshToken.incrementRefreshCount();
        save(refreshToken);
    }

}
