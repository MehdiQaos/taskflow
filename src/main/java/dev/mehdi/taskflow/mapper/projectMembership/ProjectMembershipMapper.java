package dev.mehdi.taskflow.mapper.projectMembership;

import dev.mehdi.taskflow.domain.model.ProjectMembership;
import dev.mehdi.taskflow.dto.projectMembership.ProjectMembershipDto;
import org.springframework.stereotype.Component;

@Component
public class ProjectMembershipMapper {
    public ProjectMembershipDto toDto(ProjectMembership projectMembership) {
        return new ProjectMembershipDto(
                projectMembership.getId(),
                projectMembership.getProject().getId(),
                projectMembership.getUser().getId()
        );
    }
}
