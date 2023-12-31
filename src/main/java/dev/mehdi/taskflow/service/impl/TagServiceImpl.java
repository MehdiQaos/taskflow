package dev.mehdi.taskflow.service.impl;

import dev.mehdi.taskflow.domain.model.Tag;
import dev.mehdi.taskflow.exception.ResourceExistException;
import dev.mehdi.taskflow.exception.ResourceNotFoundException;
import dev.mehdi.taskflow.repository.TagRepository;
import dev.mehdi.taskflow.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public List<Tag> getAll() {
        return tagRepository.findAll();
    }

    @Override
    public Optional<Tag> findById(Long id) {
        return tagRepository.findById(id);
    }

    @Override
    public Tag create(Tag tag) {
        tagRepository.findByName(tag.getName()).ifPresent(t -> {
            throw new ResourceExistException("Tag already exists");
        });
        return tagRepository.save(tag);
    }

    @Override
    public Tag createIfNotExist(Tag tag) {
        return tagRepository.findByName(tag.getName())
                .orElseGet(() -> create(tag));
    }

    @Override
    public void delete(Long id) {
        tagRepository.deleteById(id);
    }

    @Override
    public Tag update(Tag tag) {
        tagRepository.findById(tag.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Tag not found"));
        tagRepository.findByNameAndIdNot(tag.getName(), tag.getId()).ifPresent(
            t -> { throw new ResourceExistException("Tag with name `" + tag.getName() + "` already exists"); }
        );
        return tagRepository.save(tag);
    }
}
