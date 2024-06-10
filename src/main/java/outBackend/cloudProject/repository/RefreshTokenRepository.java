package outBackend.cloudProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import outBackend.cloudProject.security.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByKey(String key);
}
