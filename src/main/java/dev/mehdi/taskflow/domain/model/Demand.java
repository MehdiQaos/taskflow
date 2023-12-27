package dev.mehdi.taskflow.domain.model;

import dev.mehdi.taskflow.domain.model.enums.DemandStatus;
import dev.mehdi.taskflow.domain.model.enums.DemandType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Demand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Task task;

    @ManyToOne
    private Task replacementTask;

    @Enumerated(EnumType.STRING)
    private DemandType type;

    private DemandStatus status;
}