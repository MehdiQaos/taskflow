package dev.mehdi.taskflow.repository;

import dev.mehdi.taskflow.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
