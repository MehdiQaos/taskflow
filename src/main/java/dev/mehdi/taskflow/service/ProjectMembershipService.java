package dev.mehdi.taskflow.service;

import dev.mehdi.taskflow.domain.model.Project;
import dev.mehdi.taskflow.domain.model.ProjectMembership;
import dev.mehdi.taskflow.domain.model.User;

public interface ProjectMembershipService {
    ProjectMembership create(Project project, User user);
}
