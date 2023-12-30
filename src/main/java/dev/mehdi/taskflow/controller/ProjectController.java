package dev.mehdi.taskflow.controller;

import dev.mehdi.taskflow.domain.model.Project;
import dev.mehdi.taskflow.domain.model.ProjectMembership;
import dev.mehdi.taskflow.domain.model.Task;
import dev.mehdi.taskflow.dto.project.ProjectRequestDto;
import dev.mehdi.taskflow.dto.project.ProjectResponseDto;
import dev.mehdi.taskflow.dto.task.TaskRequestDto;
import dev.mehdi.taskflow.dto.task.TaskResponseDto;
import dev.mehdi.taskflow.mapper.task.TaskResponseMapper;
import dev.mehdi.taskflow.service.ProjectService;
import dev.mehdi.taskflow.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final TaskService taskService;
    private final TaskResponseMapper taskResponseMapper;

    @GetMapping
    public ResponseEntity<List<ProjectResponseDto>> getAll() {
        List<Project> projects = projectService.getAll();
        List<ProjectResponseDto> projectsDto = projects.stream()
                .map(ProjectResponseDto::fromProject)
                .toList();
        return ResponseEntity.ok(projectsDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProjectResponseDto> getById(@PathVariable Long id) {
        Project project = projectService.getById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        return ResponseEntity.ok(ProjectResponseDto.fromProject(project));
    }

    @PostMapping
    public ResponseEntity<ProjectResponseDto> create(@RequestBody ProjectRequestDto projectDto) {
        Project project = projectService.create(projectDto);
        return ResponseEntity.ok(ProjectResponseDto.fromProject(project));
    }

    @PostMapping("{id}/task")
    public ResponseEntity<TaskResponseDto> addTask(@PathVariable Long id, @Valid @RequestBody TaskRequestDto taskRequestDto) {
        Task task = taskService.addTask(id, taskRequestDto);
        return ResponseEntity.ok(taskResponseMapper.toDto(task));
    }

    @PostMapping("{projectId}/member/{memberId}")
    public ResponseEntity<Boolean> addMember(@PathVariable Long projectId, @PathVariable Long memberId) {
        ProjectMembership projectMembership = projectService.addMember(projectId, memberId);
        return ResponseEntity.ok(projectMembership != null);
    }
}
