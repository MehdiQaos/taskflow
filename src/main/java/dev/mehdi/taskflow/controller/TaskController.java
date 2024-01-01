package dev.mehdi.taskflow.controller;

import dev.mehdi.taskflow.domain.model.Task;
import dev.mehdi.taskflow.dto.task.TaskAssignDto;
import dev.mehdi.taskflow.dto.task.TaskResponseDto;
import dev.mehdi.taskflow.exception.ResourceNotFoundException;
import dev.mehdi.taskflow.mapper.task.TaskResponseMapper;
import dev.mehdi.taskflow.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final TaskResponseMapper taskResponseMapper;

    @GetMapping("{id}")
    public ResponseEntity<TaskResponseDto> getById(@PathVariable Long id) {
        Task task = taskService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        return ResponseEntity.ok(taskResponseMapper.toDto(task));
    }

    @PostMapping("{taskId}/assign")
    public ResponseEntity<TaskResponseDto> assignTask(@PathVariable Long taskId, @RequestBody TaskAssignDto taskDto) {
        Task task = taskService.assignTask(taskId, taskDto.getMemberShipId());
        return ResponseEntity.ok(taskResponseMapper.toDto(task));
    }
}
