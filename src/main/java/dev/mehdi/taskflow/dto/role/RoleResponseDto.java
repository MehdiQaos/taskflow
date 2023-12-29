package dev.mehdi.taskflow.dto.role;

import dev.mehdi.taskflow.domain.model.Role;
import dev.mehdi.taskflow.domain.model.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleResponseDto {
    private Long id;
    private String name;

    public static RoleResponseDto fromRole(Role role) {
        return new RoleResponseDto(role.getId(), role.getName().name());
    }
}
