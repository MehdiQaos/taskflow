package dev.mehdi.taskflow.domain.model;

import dev.mehdi.taskflow.domain.model.enums.ProjectStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private ProjectStatus status;
    private LocalDateTime createdAt;

    @ManyToOne
    private User owner;

    @OneToMany(mappedBy = "project")
    private final List<ProjectMembership> projectMemberships = new ArrayList<>();

    @OneToMany(mappedBy = "project")
    private final List<Task> tasks = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}
