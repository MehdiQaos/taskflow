package dev.mehdi.taskflow.controller;

import dev.mehdi.taskflow.dto.auth.AuthRequest;
import dev.mehdi.taskflow.dto.auth.AuthResponse;
import dev.mehdi.taskflow.dto.user.UserRequestDto;
import dev.mehdi.taskflow.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(
            AuthService authService
    ) {
        this.authService = authService;
    }

    @PostMapping({"login", "signin"})
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest authRequest) {
        AuthResponse authResponse = authService.authenticate(authRequest);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping({"register", "signup"})
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid UserRequestDto userRequest) {
        AuthResponse authResponse = authService.register(userRequest);
        return ResponseEntity.ok(authResponse);
    }
}
