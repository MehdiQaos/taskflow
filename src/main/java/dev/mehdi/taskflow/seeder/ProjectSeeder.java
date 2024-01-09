package dev.mehdi.taskflow.seeder;

import dev.mehdi.taskflow.dto.project.ProjectRequestDto;
import dev.mehdi.taskflow.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(5)
@RequiredArgsConstructor
public class ProjectSeeder implements CommandLineRunner {

    private final ProjectService projectService;

    private final List<ProjectRequestDto> projects = List.of(
        new ProjectRequestDto("Project 1", "Project 1 description", "ACTIVE", 1L),
        new ProjectRequestDto("Project 2", "Project 2 description", "ACTIVE", 2L),
        new ProjectRequestDto("Project 3", "Project 3 description", "ACTIVE", 3L),
        new ProjectRequestDto("Project 4", "Project 4 description", "ACTIVE", 1L),
        new ProjectRequestDto("Project 5", "Project 5 description", "ACTIVE", 1L),
        new ProjectRequestDto("Project 6", "Project 6 description", "INACTIVE", 2L)
    );

    @Override
    public void run(String... args) {
        projects.forEach(projectService::createIfNotExist);
    }
}
