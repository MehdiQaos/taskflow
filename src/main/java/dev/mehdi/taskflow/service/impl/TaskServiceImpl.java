package dev.mehdi.taskflow.service.impl;

import dev.mehdi.taskflow.domain.model.Task;
import dev.mehdi.taskflow.dto.task.TaskRequestDto;
import dev.mehdi.taskflow.repository.TaskRepository;
import dev.mehdi.taskflow.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public Task addTask(Long id, TaskRequestDto taskRequestDto) {
        return null;
    }
}
