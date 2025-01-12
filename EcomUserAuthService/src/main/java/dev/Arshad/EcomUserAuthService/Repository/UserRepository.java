package dev.Arshad.EcomUserAuthService.Repository;

import dev.Arshad.EcomUserAuthService.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
}
