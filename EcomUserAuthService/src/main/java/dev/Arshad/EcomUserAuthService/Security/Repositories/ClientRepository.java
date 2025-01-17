package dev.Arshad.EcomUserAuthService.Security.Repositories;

import dev.Arshad.EcomUserAuthService.Security.Model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    Optional<Client> findByClientId(String clientId);
}
