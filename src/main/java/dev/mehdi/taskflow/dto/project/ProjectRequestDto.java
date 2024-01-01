package dev.mehdi.taskflow.dto.project;

import dev.mehdi.taskflow.domain.model.Project;
import dev.mehdi.taskflow.domain.model.enums.ProjectStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ProjectRequestDto {
    private String name;
    private String description;
    private String status;
    private Long ownerId;

    public Project toProject() {
        return Project.builder()
                .name(name)
                .description(description)
                .build();
    }
}