package dev.mehdi.taskflow.mapper.task;

import dev.mehdi.taskflow.domain.model.Task;
import dev.mehdi.taskflow.dto.task.TaskResponseDto;
import dev.mehdi.taskflow.mapper.projectMembership.ProjectMembershipMapper;
import dev.mehdi.taskflow.mapper.tag.TagResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskResponseMapper {
    private final ProjectMembershipMapper projectMembershipMapper;
    private final TagResponseMapper tagResponseMapper;
    public TaskResponseDto toDto(Task task) {
        if (task == null) {
            return null;
        }
        return TaskResponseDto.builder()
            .id(task.getId())
            .title(task.getTitle())
            .description(task.getDescription())
            .status(task.getStatus())
            .startDate(task.getStartDate())
            .endDate(task.getEndDate())
            .projectId(task.getProject().getId())
            .assignedTo(task.getAssignedTo() != null ? projectMembershipMapper
                    .toDto(task.getAssignedTo()) : null)
            .createdBy(projectMembershipMapper.toDto(task.getCreatedBy()))
            .tags(task.getTags().stream().map(tagResponseMapper::toDto).toList())
            .build();
    }
}
