package dev.mehdi.taskflow.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToMany(mappedBy = "permissions", fetch = FetchType.EAGER)
    private final List<Role> roles = new ArrayList<>();

    public void addRole(Role role) {
        roles.add(role);
    }
}