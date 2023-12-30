package dev.mehdi.taskflow.mapper.tag;

import dev.mehdi.taskflow.domain.model.Tag;
import dev.mehdi.taskflow.dto.tag.TagRequestDto;
import org.springframework.stereotype.Component;

@Component
public class TagRequestMapper {
    public Tag toTag(TagRequestDto tagRequestDto) {
        return Tag.builder()
                .name(tagRequestDto.getName())
                .description(tagRequestDto.getDescription())
                .build();
    }
}
