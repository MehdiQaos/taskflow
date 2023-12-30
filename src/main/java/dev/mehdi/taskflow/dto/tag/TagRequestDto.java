package dev.mehdi.taskflow.dto.tag;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TagRequestDto {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;
}