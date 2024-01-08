package dev.mehdi.taskflow.service.impl;

import dev.mehdi.taskflow.domain.model.Token;
import dev.mehdi.taskflow.domain.model.User;
import dev.mehdi.taskflow.domain.model.enums.TokenType;
import dev.mehdi.taskflow.dto.auth.AuthRequest;
import dev.mehdi.taskflow.dto.auth.AuthResponse;
import dev.mehdi.taskflow.dto.user.UserRequestDto;
import dev.mehdi.taskflow.service.AuthService;
import dev.mehdi.taskflow.service.JwtService;
import dev.mehdi.taskflow.service.TokenService;
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
    private final TokenService tokenService;

    public AuthServiceImpl(
            UserService userService,
            JwtService jwtService,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            TokenService tokenService
    ) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
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
        tokenService.revokeAllTokens(user);
        String jwtToken = createToken(user);

        return authenticationResponse(jwtToken);
    }

    private String createToken(User user) {
        String jwtToken = jwtService.generateToken(user);
        Token token = Token.builder()
                .user(user)
                .token(jwtToken)
                .isValid(true)
                .type(TokenType.BEARER)
                .build();
        tokenService.save(token);
        return jwtToken;
    }

    @Override
    public AuthResponse register(UserRequestDto userRequest) {
        String hashedPassword = passwordEncoder.encode(userRequest.getPassword());
        userRequest.setPassword(hashedPassword);
        User user = userService.create(userRequest);
        String jwtToken = createToken(user);

        return authenticationResponse(jwtToken);
    }

    private AuthResponse authenticationResponse(String token) {
        return AuthResponse.builder().token(token).build();
    }
}
