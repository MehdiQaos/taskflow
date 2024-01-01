package dev.mehdi.taskflow.domain.model;

import dev.mehdi.taskflow.domain.model.enums.DemandeStatus;
import dev.mehdi.taskflow.domain.model.enums.DemandeType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Demande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reason;

    private LocalDateTime createdAt;

    @ManyToOne
    private ProjectMembership projectMembership;

    @ManyToOne
    private Task task;

    @ManyToOne
    private Task replacementTask;

    @Enumerated(EnumType.STRING)
    private DemandeType type;

    @Enumerated(EnumType.STRING)
    private DemandeStatus status;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

}