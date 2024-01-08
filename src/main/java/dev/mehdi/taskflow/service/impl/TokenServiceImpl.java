package dev.mehdi.taskflow.service.impl;

import dev.mehdi.taskflow.domain.model.Token;
import dev.mehdi.taskflow.domain.model.User;
import dev.mehdi.taskflow.repository.TokenRepository;
import dev.mehdi.taskflow.service.TokenService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TokenServiceImpl implements TokenService {
    private final TokenRepository tokenRepository;

    public TokenServiceImpl(
            TokenRepository tokenRepository
    ) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public Token save(Token token) {
        return tokenRepository.save(token);
    }

    public Optional<Token> getByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    public void revokeAllTokens(User user) {
        tokenRepository.revokeAllByUser(user);
    }
}
