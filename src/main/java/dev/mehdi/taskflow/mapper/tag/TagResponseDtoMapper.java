package dev.mehdi.taskflow.mapper.tag;

import dev.mehdi.taskflow.domain.model.Tag;
import dev.mehdi.taskflow.dto.tag.TagResponseDto;
import org.springframework.stereotype.Component;

@Component
public class TagResponseDtoMapper {
    public TagResponseDto toDto(Tag tag) {
        return new TagResponseDto(
                tag.getId(),
                tag.getName(),
                tag.getDescription()
        );
    }
}
