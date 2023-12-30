package dev.mehdi.taskflow.seeder;

import dev.mehdi.taskflow.dto.tag.TagRequestDto;
import dev.mehdi.taskflow.mapper.tag.TagRequestMapper;
import dev.mehdi.taskflow.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(2)
@RequiredArgsConstructor
public class TagSeeder implements CommandLineRunner {

    private final TagService tagService;
    private final TagRequestMapper tagRequestMapper;
    private final List<TagRequestDto> tags = List.of(
        new TagRequestDto("Java", "Java programming language"),
        new TagRequestDto("Spring", "Spring framework"),
        new TagRequestDto("Hibernate", "Hibernate ORM framework"),
        new TagRequestDto("JPA", "Java Persistence API"),
        new TagRequestDto("Jakarta EE", "Jakarta Enterprise Edition")
    );

    @Override
    public void run(String... args) {
        tags.stream()
                .map(tagRequestMapper::toTag)
                .forEach(tagService::createIfNotExist);
    }
}