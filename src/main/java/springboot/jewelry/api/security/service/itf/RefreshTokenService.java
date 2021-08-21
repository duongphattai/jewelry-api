package springboot.jewelry.api.security.service.itf;

import springboot.jewelry.api.security.model.RefreshToken;

import java.util.Optional;

public interface RefreshTokenService {

    Optional<RefreshToken> findByToken(String token);

    RefreshToken save(RefreshToken refreshToken);

    RefreshToken createRefreshToken();

    void verifyExpiration(RefreshToken token);

    void deleteById(Long id);

    void increaseCount(RefreshToken refreshToken);
}
