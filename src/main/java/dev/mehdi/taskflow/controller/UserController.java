package dev.mehdi.taskflow.controller;

import dev.mehdi.taskflow.domain.model.User;
import dev.mehdi.taskflow.dto.user.UserRequestDto;
import dev.mehdi.taskflow.dto.user.UserResponseDto;
import dev.mehdi.taskflow.service.UserService;
import jakarta.validation.Valid;
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
    ResponseEntity<List<UserResponseDto>> all() {
        List<User> users = userService.getAll();
        return ResponseEntity.ok(users
                .stream()
                .map(UserResponseDto::fromUser)
                .toList());
    }

    @GetMapping("{id}")
    ResponseEntity<UserResponseDto> getById(@PathVariable Long id) {
        User user = userService.getById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(UserResponseDto.fromUser(user));
    }

    @PostMapping
    ResponseEntity<UserResponseDto> create(@RequestBody @Valid UserRequestDto userDto) {
        User user = userService.create(userDto);
        return ResponseEntity.ok(UserResponseDto.fromUser(user));
    }
}
