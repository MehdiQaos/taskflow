package dev.mehdi.taskflow.service.impl;

import dev.mehdi.taskflow.domain.model.Project;
import dev.mehdi.taskflow.domain.model.ProjectMembership;
import dev.mehdi.taskflow.domain.model.User;
import dev.mehdi.taskflow.domain.model.enums.ProjectStatus;
import dev.mehdi.taskflow.dto.project.ProjectRequestDto;
import dev.mehdi.taskflow.dto.task.TaskRequestDto;
import dev.mehdi.taskflow.exception.ResourceNotFoundException;
import dev.mehdi.taskflow.repository.ProjectRepository;
import dev.mehdi.taskflow.service.ProjectMembershipService;
import dev.mehdi.taskflow.service.ProjectService;
import dev.mehdi.taskflow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserService userService;
    private final ProjectMembershipService projectMembershipService;
    @Override
    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    @Override
    public Optional<Project> getById(Long id) {
        return projectRepository.findById(id);
    }

    @Override
    public Project create(ProjectRequestDto projectDto) {
        Project newProject = projectDto.toProject();
        User owner = userService.getById(projectDto.getOwnerId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + projectDto.getOwnerId()));
        newProject.setOwner(owner);
        ProjectStatus status;
        try {
            status = ProjectStatus.valueOf(projectDto.getStatus());
        } catch (IllegalArgumentException e) {
            throw new ResourceNotFoundException("Invalid project status");
        }
//        newProject.setCreatedAt(LocalDateTime.now());
        newProject.setStatus(status);
        return projectRepository.save(newProject);
    }

    @Override
    public Project addTask(Long id, TaskRequestDto taskRequestDto) {
        return null;
    }

    @Override
    public ProjectMembership addMember(Long projectId, Long userId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + projectId));
        User user = userService.getById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        return projectMembershipService.create(project, user);
    }
}
