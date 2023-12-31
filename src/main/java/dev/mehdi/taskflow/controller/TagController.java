package dev.mehdi.taskflow.controller;

import dev.mehdi.taskflow.domain.model.Tag;
import dev.mehdi.taskflow.dto.tag.TagRequestDto;
import dev.mehdi.taskflow.dto.tag.TagResponseDto;
import dev.mehdi.taskflow.exception.ResourceNotFoundException;
import dev.mehdi.taskflow.mapper.tag.TagRequestMapper;
import dev.mehdi.taskflow.mapper.tag.TagResponseMapper;
import dev.mehdi.taskflow.service.TagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;
    private final TagResponseMapper tagResponseMapper;
    private final TagRequestMapper tagRequestMapper;

    @GetMapping
    public ResponseEntity<List<TagResponseDto>> all() {
        List<TagResponseDto> tags = tagService.getAll().stream()
                .map(tagResponseMapper::toDto)
                .toList();
        return ResponseEntity.ok(tags);
    }

    @GetMapping("{id}")
    public ResponseEntity<TagResponseDto> getById(@PathVariable Long id) {
        Tag tag = tagService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tag not found"));
        return ResponseEntity.ok(tagResponseMapper.toDto(tag));
    }

    @PostMapping
    public ResponseEntity<TagResponseDto> create(@RequestBody @Valid TagRequestDto tagDto) {
        Tag tag = tagRequestMapper.toTag(tagDto);
        tag = tagService.create(tag);
        return ResponseEntity.ok(tagResponseMapper.toDto(tag));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tagService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<TagResponseDto> update(@PathVariable Long id, @RequestBody @Valid TagRequestDto tagDto) {
        Tag tag = tagRequestMapper.toTag(tagDto);
        tag.setId(id);
        tag = tagService.update(tag);
        return ResponseEntity.ok(tagResponseMapper.toDto(tag));
    }
}
