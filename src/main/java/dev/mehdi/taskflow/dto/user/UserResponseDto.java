package dev.mehdi.taskflow.dto.user;

import dev.mehdi.taskflow.domain.model.User;
import dev.mehdi.taskflow.dto.role.RoleResponseDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<RoleResponseDto> roles;

    public static UserResponseDto from(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .roles(user.getRoles()
                        .stream()
                        .map(RoleResponseDto::fromRole)
                        .toList()
                )
                .build();
    }
}
