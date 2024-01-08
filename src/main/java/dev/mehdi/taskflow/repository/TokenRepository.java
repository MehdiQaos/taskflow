package dev.mehdi.taskflow.repository;

import dev.mehdi.taskflow.domain.model.Token;
import dev.mehdi.taskflow.domain.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByToken(String token);

    @Transactional
    @Modifying
    @Query("UPDATE Token t SET t.isValid = false WHERE t.user = ?1 AND t.isValid = true")
    void revokeAllByUser(User user);
}