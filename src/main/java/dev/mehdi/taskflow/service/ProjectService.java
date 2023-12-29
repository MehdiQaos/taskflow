package dev.mehdi.taskflow.service;

import dev.mehdi.taskflow.domain.model.Project;
import dev.mehdi.taskflow.domain.model.ProjectMembership;
import dev.mehdi.taskflow.dto.project.ProjectRequestDto;
import dev.mehdi.taskflow.dto.task.TaskRequestDto;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    List<Project> getAll();

    Optional<Project> getById(Long id);

    Project create(ProjectRequestDto projectDto);

    Project addTask(Long id, TaskRequestDto taskRequestDto);

    ProjectMembership addMember(Long projectId, Long userId);
}
