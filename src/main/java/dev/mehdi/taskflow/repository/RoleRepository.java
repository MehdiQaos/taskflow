package dev.mehdi.taskflow.repository;

import dev.mehdi.taskflow.domain.model.Role;
import dev.mehdi.taskflow.domain.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(UserRole name);
}
