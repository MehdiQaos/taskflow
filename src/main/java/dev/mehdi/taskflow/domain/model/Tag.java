package dev.mehdi.taskflow.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToMany(mappedBy = "tags")
    private final List<Task> tasks = new ArrayList<>();
}