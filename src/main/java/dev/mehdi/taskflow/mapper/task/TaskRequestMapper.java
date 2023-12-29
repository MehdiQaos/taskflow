package dev.mehdi.taskflow.mapper.task;

import dev.mehdi.taskflow.domain.model.Project;
import dev.mehdi.taskflow.domain.model.Task;
import dev.mehdi.taskflow.domain.model.enums.TaskStatus;
import dev.mehdi.taskflow.dto.task.TaskRequestDto;
import dev.mehdi.taskflow.exception.InvalidRequestException;
import dev.mehdi.taskflow.service.ProjectService;
import dev.mehdi.taskflow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TaskRequestMapper {
    private final ProjectService projectService;
    private final UserService userService;
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
            taskStatus = TaskStatus.values()[taskRequestDto.getStatusId()];
            task.setStatus(taskStatus);
        } catch (ArrayIndexOutOfBoundsException e) {
            errors.put("statusId", "not valid status id: " + taskRequestDto.getStatusId());
        }

        Project project;
        Optional<Project> optionalProject = projectService.getById(taskRequestDto.getProjectId());
        if (optionalProject.isEmpty()) {
            errors.put("projectId", "not valid project id: " + taskRequestDto.getProjectId());
        } else {
            project = optionalProject.get();
            task.setProject(project);
        }

//        Optional<User> optionalCreatedBy = userService.getById(taskRequestDto.getCreatedById());
//        if (optionalCreatedBy.isEmpty()) {
//            errors.put("createdById", "not valid user id: " + taskRequestDto.getCreatedById());
//        } else {
//            User createdBy = optionalCreatedBy.get();
//            task.setCreatedBy(createdBy);
//        }

        if (!errors.isEmpty()) {
            throw new InvalidRequestException("invalid task request", errors);
        }
        return task;
    }
}
