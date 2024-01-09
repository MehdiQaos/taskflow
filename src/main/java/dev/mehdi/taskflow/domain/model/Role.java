package dev.mehdi.taskflow.domain.model;

import dev.mehdi.taskflow.domain.model.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private UserRole name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private final List<User> users = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private final List<Permission> permissions = new ArrayList<>();

    public void addPermission(Permission permission) {
        permissions.add(permission);
    }
}
