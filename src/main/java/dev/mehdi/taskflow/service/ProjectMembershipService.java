package dev.mehdi.taskflow.service;

import dev.mehdi.taskflow.domain.model.Project;
import dev.mehdi.taskflow.domain.model.ProjectMembership;
import dev.mehdi.taskflow.domain.model.User;

import java.util.Optional;

public interface ProjectMembershipService {
    ProjectMembership create(Project project, User user);
    Optional<ProjectMembership> findByProjectAndUser(Project project, User user);
    Optional<ProjectMembership> getById(Long memberShipId);
}
