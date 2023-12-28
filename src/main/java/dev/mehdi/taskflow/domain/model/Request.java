package dev.mehdi.taskflow.domain.model;

import dev.mehdi.taskflow.domain.model.enums.RequestStatus;
import dev.mehdi.taskflow.domain.model.enums.RequestType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Request {
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
    private RequestType type;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;
}