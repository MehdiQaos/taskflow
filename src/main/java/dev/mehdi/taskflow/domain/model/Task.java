package dev.mehdi.taskflow.domain.model;

import dev.mehdi.taskflow.domain.model.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @ManyToOne
    private Project project;

    @ManyToOne
    private ProjectMembership assignedTo;

    @ManyToOne
    private ProjectMembership createdBy;

    @ManyToMany
    private final List<Tag> tags = new ArrayList<>();
}
