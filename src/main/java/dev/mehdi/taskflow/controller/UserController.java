package dev.mehdi.taskflow.controller;

import dev.mehdi.taskflow.domain.model.User;
import dev.mehdi.taskflow.dto.user.UserRequestDto;
import dev.mehdi.taskflow.dto.user.UserResponseDto;
import dev.mehdi.taskflow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    ResponseEntity<List<User>> all() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("{id}")
    ResponseEntity<User> getById(@PathVariable Long id) {
        User user = userService.getById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(user);
    }

    @PostMapping
    ResponseEntity<UserResponseDto> create(@RequestBody UserRequestDto userDto) {
        User user = userService.create(userDto);
        return ResponseEntity.ok(UserResponseDto.from(user));
    }
}
