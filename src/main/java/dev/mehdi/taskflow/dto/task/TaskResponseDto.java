package dev.mehdi.taskflow.dto.task;

import dev.mehdi.taskflow.domain.model.Tag;
import dev.mehdi.taskflow.domain.model.enums.TaskStatus;
import dev.mehdi.taskflow.dto.projectMembership.ProjectMembershipDto;
import dev.mehdi.taskflow.dto.tag.TagResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter @Setter
@Builder
public class TaskResponseDto {
    private Long id;

    private String title;

    private String description;

    private TaskStatus status;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Long projectId;

    private ProjectMembershipDto assignedTo;

    private ProjectMembershipDto createdBy;

    private List<TagResponseDto> tags;
}
