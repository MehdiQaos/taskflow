package dev.mehdi.taskflow.dto.tag;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TagResponseDto {
    private Long id;
    private String name;
    private String description;
}
