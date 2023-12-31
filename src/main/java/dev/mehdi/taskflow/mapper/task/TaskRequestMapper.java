package dev.mehdi.taskflow.mapper.task;

import dev.mehdi.taskflow.domain.model.Task;
import dev.mehdi.taskflow.domain.model.enums.TaskStatus;
import dev.mehdi.taskflow.dto.task.TaskRequestDto;
import dev.mehdi.taskflow.exception.InvalidRequestException;
import dev.mehdi.taskflow.service.ProjectMembershipService;
import dev.mehdi.taskflow.service.ProjectService;
import dev.mehdi.taskflow.service.TagService;
import dev.mehdi.taskflow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TaskRequestMapper {

    private final ProjectService projectService;
    private final UserService userService;
    private final TagService tagService;
    private final ProjectMembershipService projectMembershipService;

    public Task toTask(TaskRequestDto taskRequestDto) {
        Map<String, String> errors = new HashMap<>();
        Task task = Task.builder()
                .title(taskRequestDto.getTitle())
                .description(taskRequestDto.getDescription())
                .startDate(taskRequestDto.getStartDate())
                .endDate(taskRequestDto.getEndDate())
                .build();

        TaskStatus taskStatus;
        try {
            int statusId = taskRequestDto.getStatusId();
            taskStatus = TaskStatus.values()[statusId];
            task.setStatus(taskStatus);
        } catch (ArrayIndexOutOfBoundsException e) {
            errors.put("statusId", "not valid status id: " + taskRequestDto.getStatusId());
        }

        projectService.getById(taskRequestDto.getProjectId()).ifPresentOrElse(
            project -> {
                task.setProject(project);
                userService.getById(taskRequestDto.getCreatorId()).ifPresentOrElse(
                    creatorUser -> {
                        projectMembershipService.findByProjectAndUser(project, creatorUser).ifPresentOrElse(
                            task::setCreatedBy,
                            () -> errors.put("createdById", "project not created by user id: " + taskRequestDto.getCreatorId())
                        );
                    },
                    () -> errors.put("createdById", "not valid user id: " + taskRequestDto.getCreatorId())
                );
            },
            () -> errors.put("projectId", "not valid project id: " + taskRequestDto.getProjectId())
        );

        StringBuffer tagsErrors = new StringBuffer();
        System.out.println("tags request: " + taskRequestDto.getTagsIds());
        taskRequestDto.getTagsIds().forEach(tagId -> {
            tagService.findById(tagId).ifPresentOrElse(
                task::addTag,
                () -> tagsErrors.append("tag not found id: ").append(tagId).append(";")
            );
        });
        if (!tagsErrors.isEmpty()) {
            errors.put("tags", tagsErrors.toString());
        }

        if (!errors.isEmpty()) {
            throw new InvalidRequestException("invalid task request", errors);
        }
        return task;
    }
}
