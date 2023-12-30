package dev.mehdi.taskflow.dto.projectMembership;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class ProjectMembershipDto {
    private Long membershipId;
    private Long projectId;
    private Long userId;
}
