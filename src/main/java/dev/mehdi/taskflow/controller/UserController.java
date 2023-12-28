package dev.mehdi.taskflow.controller;

import dev.mehdi.taskflow.domain.model.User;
import dev.mehdi.taskflow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
//@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

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
}
