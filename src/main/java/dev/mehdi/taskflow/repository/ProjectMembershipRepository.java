package dev.mehdi.taskflow.repository;

import dev.mehdi.taskflow.domain.model.ProjectMembership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectMembershipRepository extends JpaRepository<ProjectMembership, Long> {
}