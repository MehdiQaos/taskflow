package dev.mehdi.taskflow.dto.role;

import dev.mehdi.taskflow.domain.model.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleResponseDto {
    private String name;
}
