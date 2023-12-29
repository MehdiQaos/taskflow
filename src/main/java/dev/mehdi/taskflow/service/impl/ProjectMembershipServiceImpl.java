package dev.mehdi.taskflow.service.impl;

import dev.mehdi.taskflow.domain.model.Project;
import dev.mehdi.taskflow.domain.model.ProjectMembership;
import dev.mehdi.taskflow.domain.model.User;
import dev.mehdi.taskflow.repository.ProjectMembershipRepository;
import dev.mehdi.taskflow.service.ProjectMembershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectMembershipServiceImpl implements ProjectMembershipService {
    private final ProjectMembershipRepository projectMembershipRepository;
    @Override
    public ProjectMembership create(Project project, User user) {
        projectMembershipRepository.findByProjectAndUser(project, user)
                .ifPresent(projectMembership -> {
                    throw new RuntimeException("User already member of this project");
                });
        ProjectMembership projectMembership = ProjectMembership.builder()
                .project(project)
                .user(user)
                .build();
        return projectMembershipRepository.save(projectMembership);
    }
}
