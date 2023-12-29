package dev.mehdi.taskflow.dto.project;

import dev.mehdi.taskflow.domain.model.Project;
import dev.mehdi.taskflow.domain.model.enums.ProjectStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ProjectResponseDto {
    private Long id;
    private String name;
    private String description;
    private ProjectStatus status;
    private LocalDateTime createdAt;

    public static ProjectResponseDto fromProject(Project project) {
        return ProjectResponseDto.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .status(project.getStatus())
                .createdAt(project.getCreatedAt())
                .build();
    }
}
