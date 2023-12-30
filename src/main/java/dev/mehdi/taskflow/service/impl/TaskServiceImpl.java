package dev.mehdi.taskflow.service.impl;

import dev.mehdi.taskflow.domain.model.Task;
import dev.mehdi.taskflow.dto.task.TaskRequestDto;
import dev.mehdi.taskflow.exception.InvalidRequestException;
import dev.mehdi.taskflow.mapper.task.TaskRequestMapper;
import dev.mehdi.taskflow.repository.TaskRepository;
import dev.mehdi.taskflow.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskRequestMapper taskRequestMapper;

    @Override
    public Task addTask(Long id, TaskRequestDto taskRequestDto) {
        Task task = taskRequestMapper.toTask(taskRequestDto);
        System.out.println("tags: ");
        task.getTags().forEach(tag -> System.out.println("tag: " + tag.getName()));

        LocalDateTime startDate = task.getStartDate();
        LocalDateTime endDate = task.getEndDate();
        if (startDate.isAfter(endDate)) {
            throw InvalidRequestException.of("date", "end date must be after start date");
        }

        return taskRepository.save(task);
    }
}
