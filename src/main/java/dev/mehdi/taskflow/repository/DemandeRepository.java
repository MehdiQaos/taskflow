package dev.mehdi.taskflow.repository;

import dev.mehdi.taskflow.domain.model.Demande;
import dev.mehdi.taskflow.domain.model.enums.DemandeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DemandeRepository extends JpaRepository<Demande, Long> {
    List<Demande> findAllByTypeAndCreatedAtIsAfter(DemandeType type, LocalDateTime createdAt);
}
