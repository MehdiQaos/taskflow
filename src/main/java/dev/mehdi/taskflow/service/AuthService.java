package dev.mehdi.taskflow.service;

import dev.mehdi.taskflow.domain.model.User;
import dev.mehdi.taskflow.dto.auth.AuthRequest;
import dev.mehdi.taskflow.dto.auth.AuthResponse;
import dev.mehdi.taskflow.dto.user.UserRequestDto;

public interface AuthService {
    AuthResponse authenticate(AuthRequest authRequest);

    AuthResponse register(UserRequestDto userRequest);
}
