package dev.Arshad.EcomUserAuthService.Repository;

import dev.Arshad.EcomUserAuthService.Entity.Session;
import dev.Arshad.EcomUserAuthService.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface SessionRepository extends JpaRepository<Session,UUID> {
    Optional<Session> findByToken(String token);
    Optional<Session> findByTokenAndUser_Id(String token ,UUID userId);
}
