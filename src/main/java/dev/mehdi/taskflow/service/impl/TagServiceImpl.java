package dev.mehdi.taskflow.service.impl;

import dev.mehdi.taskflow.domain.model.Tag;
import dev.mehdi.taskflow.repository.TagRepository;
import dev.mehdi.taskflow.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public Optional<Tag> findById(Long id) {
        return tagRepository.findById(id);
    }

    @Override
    public Tag createIfNotExist(Tag tag) {
        return tagRepository.findByName(tag.getName())
                .orElseGet(() -> tagRepository.save(tag));
    }
}
