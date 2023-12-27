package dev.mehdi.taskflow.domain.model;

import dev.mehdi.taskflow.domain.model.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

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
    private List<User> users;

    @ManyToMany
    private List<Permission> permissions;
}
