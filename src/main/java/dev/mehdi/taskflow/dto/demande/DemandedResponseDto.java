package dev.mehdi.taskflow.dto.demande;

import dev.mehdi.taskflow.domain.model.ProjectMembership;
import dev.mehdi.taskflow.domain.model.enums.DemandeStatus;
import dev.mehdi.taskflow.domain.model.enums.DemandeType;
import dev.mehdi.taskflow.dto.projectMembership.ProjectMembershipDto;
import dev.mehdi.taskflow.dto.task.TaskResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@Builder
public class DemandedResponseDto {
    private Long id;

    private String reason;

    private LocalDateTime createdAt;

    private ProjectMembershipDto projectMembership;

    private TaskResponseDto task;

    private TaskResponseDto replacementTask;

    private DemandeType type;

    private DemandeStatus status;
}