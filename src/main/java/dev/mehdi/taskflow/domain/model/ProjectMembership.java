package dev.mehdi.taskflow.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "project_id"})
)
public class ProjectMembership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @OneToMany(mappedBy = "assignedTo")
    private final List<Task> tasks = new ArrayList<>();

    @OneToMany(mappedBy = "projectMembership")
    private final List<Demande> demandes = new ArrayList<>();

    @OneToMany(mappedBy = "createdBy")
    private final List<Task> createdTasks = new ArrayList<>();

}
