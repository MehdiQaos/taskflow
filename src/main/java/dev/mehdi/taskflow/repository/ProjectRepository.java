package dev.mehdi.taskflow.repository;

import dev.mehdi.taskflow.domain.model.Project;
import dev.mehdi.taskflow.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findByNameAndOwner(String name, User owner);
}
