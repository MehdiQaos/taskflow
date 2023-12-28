package dev.mehdi.taskflow.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "_user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "owner")
    private final List<Project> ownProjects = new ArrayList<>();

    @ManyToMany
    private final List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private final List<ProjectMembership> projectMemberships = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public void addRole(Role role) {
        roles.add(role);
    }
}
