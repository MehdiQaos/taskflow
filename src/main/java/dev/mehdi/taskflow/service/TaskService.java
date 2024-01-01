package dev.mehdi.taskflow.service;

import dev.mehdi.taskflow.domain.model.Project;
import dev.mehdi.taskflow.domain.model.Task;
import dev.mehdi.taskflow.dto.task.TaskRequestDto;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    Task addTask(Long id, TaskRequestDto taskRequestDto);

    Task assignTask(Long taskId, Long memberShipId);

    Optional<Task> getById(Long id);

    List<Task> getAllByProjectId(Long projectId);
}
