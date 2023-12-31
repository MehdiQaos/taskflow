package dev.mehdi.taskflow.service;

import dev.mehdi.taskflow.domain.model.Tag;
import dev.mehdi.taskflow.dto.tag.TagRequestDto;

import java.util.List;
import java.util.Optional;

public interface TagService {
    List<Tag> getAll();

    Optional<Tag> findById(Long id);

    Tag create(Tag tag);

    Tag createIfNotExist(Tag tag);

    void delete(Long id);

    Tag update(Tag tag);
}
