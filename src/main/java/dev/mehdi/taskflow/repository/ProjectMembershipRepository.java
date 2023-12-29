package dev.mehdi.taskflow.repository;

import dev.mehdi.taskflow.domain.model.Project;
import dev.mehdi.taskflow.domain.model.ProjectMembership;
import dev.mehdi.taskflow.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectMembershipRepository extends JpaRepository<ProjectMembership, Long> {
    Optional<ProjectMembership> findByProjectAndUser(Project project, User user);
}