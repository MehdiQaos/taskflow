package dev.mehdi.taskflow.service;

import dev.mehdi.taskflow.domain.model.Token;
import dev.mehdi.taskflow.domain.model.User;

import java.util.Optional;

public interface TokenService {
    Token save(Token token);
    void revokeAllTokens(User user);
    Optional<Token> getByToken(String token);
}
