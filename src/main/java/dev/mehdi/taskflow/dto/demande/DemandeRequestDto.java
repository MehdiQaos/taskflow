package dev.mehdi.taskflow.dto.demande;

import dev.mehdi.taskflow.domain.model.ProjectMembership;
import dev.mehdi.taskflow.domain.model.Task;
import dev.mehdi.taskflow.domain.model.enums.DemandeType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DemandeRequestDto {
    private String reason;
    private Long membershipId;
    private String demandeType;
    private Long taskId;
    private Long replacementTaskId;
}