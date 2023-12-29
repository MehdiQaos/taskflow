package dev.mehdi.taskflow.service;

import dev.mehdi.taskflow.domain.model.Project;
import dev.mehdi.taskflow.domain.model.Task;
import dev.mehdi.taskflow.dto.task.TaskRequestDto;

public interface TaskService {
    Task addTask(Long id, TaskRequestDto taskRequestDto);
}
