package dev.mehdi.taskflow.service.impl;

import dev.mehdi.taskflow.domain.model.ProjectMembership;
import dev.mehdi.taskflow.domain.model.Task;
import dev.mehdi.taskflow.dto.task.TaskRequestDto;
import dev.mehdi.taskflow.exception.InvalidRequestException;
import dev.mehdi.taskflow.mapper.task.TaskRequestMapper;
import dev.mehdi.taskflow.repository.TaskRepository;
import dev.mehdi.taskflow.service.ProjectMembershipService;
import dev.mehdi.taskflow.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskRequestMapper taskRequestMapper;
    private final ProjectMembershipService projectMembershipService;

    @Override
    public Task addTask(Long id, TaskRequestDto taskRequestDto) {
        Task task = taskRequestMapper.toTask(taskRequestDto);

        LocalDateTime startDate = task.getStartDate();
        LocalDateTime endDate = task.getEndDate();
        if (startDate.isAfter(endDate)) {
            throw InvalidRequestException.of("date", "end date must be after start date");
        }

        return taskRepository.save(task);
    }

    @Override
    public Task assignTask(Long taskId, Long memberShipId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        if (task.getEndDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Task deadline has ended");
        }
        if (task.getAssignedTo() != null) {
            throw new RuntimeException("Task already assigned");
        }
        ProjectMembership projectMembership = projectMembershipService.getById(memberShipId)
                .orElseThrow(() -> new RuntimeException("user is not member of this project"));
        task.setAssignedTo(projectMembership);
        return taskRepository.save(task);
    }

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Optional<Task> getById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public List<Task> getAllByProjectId(Long projectId) {
        return taskRepository.getAllByProjectId(projectId);
    }
}
