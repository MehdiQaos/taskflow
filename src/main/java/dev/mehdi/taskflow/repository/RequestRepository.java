package dev.mehdi.taskflow.repository;

import dev.mehdi.taskflow.domain.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
}
