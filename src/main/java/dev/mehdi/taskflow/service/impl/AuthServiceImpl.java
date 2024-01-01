package dev.mehdi.taskflow.service.impl;

import dev.mehdi.taskflow.domain.model.User;
import dev.mehdi.taskflow.dto.auth.AuthRequest;
import dev.mehdi.taskflow.dto.auth.AuthResponse;
import dev.mehdi.taskflow.dto.user.UserRequestDto;
import dev.mehdi.taskflow.service.AuthService;
import dev.mehdi.taskflow.service.JwtService;
import dev.mehdi.taskflow.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(
            UserService userService,
            JwtService jwtService,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthResponse authenticate(AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail(), authRequest.getPassword()
                )
        );
        User user = userService.getByEmail(authRequest.getEmail())
                .orElseThrow();
        return getAuthenticationResponse(user);
    }

    @Override
    public AuthResponse register(UserRequestDto userRequest) {
        String hashedPassword = passwordEncoder.encode(userRequest.getPassword());
        userRequest.setPassword(hashedPassword);
        User user = userService.create(userRequest);

        return getAuthenticationResponse(user);
    }

    @Override
    public AuthResponse getAuthenticationResponse(User user) {
        var token = jwtService.generateToken(user);
        return AuthResponse
                .builder()
                .token(token)
                .build();
    }
}
