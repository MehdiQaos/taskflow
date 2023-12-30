package dev.mehdi.taskflow.service;

import dev.mehdi.taskflow.domain.model.Tag;

import java.util.Optional;

public interface TagService {
    Optional<Tag> findById(Long id);
    Tag createIfNotExist(Tag tag);
}
