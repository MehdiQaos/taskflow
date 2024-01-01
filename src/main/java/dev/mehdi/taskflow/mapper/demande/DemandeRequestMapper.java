package dev.mehdi.taskflow.mapper.demande;

import dev.mehdi.taskflow.domain.model.Demande;
import dev.mehdi.taskflow.domain.model.ProjectMembership;
import dev.mehdi.taskflow.domain.model.Task;
import dev.mehdi.taskflow.domain.model.enums.DemandeType;
import dev.mehdi.taskflow.dto.demande.DemandeRequestDto;
import dev.mehdi.taskflow.exception.InvalidRequestException;
import dev.mehdi.taskflow.service.ProjectMembershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DemandeRequestMapper {

    private final ProjectMembershipService projectMembershipService;

    public Demande toDemande(DemandeRequestDto demandeRequestDto) {
        Demande demande = new Demande();

        demande.setReason(demandeRequestDto.getReason());
        try {
            demande.setType(DemandeType.valueOf(demandeRequestDto.getDemandeType()));
        } catch (Exception e) {
            throw InvalidRequestException.of("type", "Invalid demande type");
        }

        ProjectMembership membership = projectMembershipService.getById(demandeRequestDto.getMembershipId())
                .orElseThrow(() -> InvalidRequestException.of("membershipId", "Invalid membership id"));
        demande.setProjectMembership(membership);

        Task task = membership.getProject().getTasks().stream()
                .filter(t -> t.getId().equals(demandeRequestDto.getTaskId()))
                .findFirst()
                .orElseThrow(() -> InvalidRequestException.of("taskId", "Invalid task id"));
        demande.setTask(task);

        if (demandeRequestDto.getReplacementTaskId() != null) {
            Task replacementTask = membership.getProject().getTasks().stream()
                    .filter(t -> t.getId().equals(demandeRequestDto.getReplacementTaskId()))
                    .findFirst()
                    .orElseThrow(() -> InvalidRequestException.of("replacementTaskId", "Invalid replacement task id"));
            demande.setReplacementTask(replacementTask);
        }

        return demande;
    }
}
