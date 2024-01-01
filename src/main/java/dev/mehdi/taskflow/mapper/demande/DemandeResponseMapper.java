package dev.mehdi.taskflow.mapper.demande;

import dev.mehdi.taskflow.domain.model.Demande;
import dev.mehdi.taskflow.dto.demande.DemandedResponseDto;
import dev.mehdi.taskflow.mapper.projectMembership.ProjectMembershipMapper;
import dev.mehdi.taskflow.mapper.task.TaskResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DemandeResponseMapper {

    private final ProjectMembershipMapper projectMembershipMapper;
    private final TaskResponseMapper taskResponseMapper;
    public DemandedResponseDto toDemandedResponseDto(Demande demande) {
        DemandedResponseDto demandedResponseDto = DemandedResponseDto.builder()
                .id(demande.getId())
                .reason(demande.getReason())
                .createdAt(demande.getCreatedAt())
                .type(demande.getType())
                .status(demande.getStatus())
                .build();

        demandedResponseDto.setProjectMembership(
                projectMembershipMapper.toDto(demande.getProjectMembership()));
        demandedResponseDto.setTask(taskResponseMapper.toDto(demande.getTask()));
        demandedResponseDto.setReplacementTask(
                taskResponseMapper.toDto(demande.getReplacementTask()));

        return demandedResponseDto;
    }
}
