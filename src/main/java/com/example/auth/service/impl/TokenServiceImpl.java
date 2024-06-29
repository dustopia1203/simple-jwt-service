package com.example.auth.service.impl;

import com.example.auth.model.entity.Token;
import com.example.auth.repository.TokenRepository;
import com.example.auth.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;

    @Override
    public void revokeAllTokensOfUser(String username) {
        List<Token> tokens = tokenRepository.findAllValidToken(username);
        if (tokens.isEmpty()) return;
        tokens.forEach(token -> token.setRevoked(true));
        tokenRepository.saveAll(tokens);
    }

    @Override
    public void saveToken(String username, String token) {
        tokenRepository.save(Token.builder().username(username).type("BEARER").token(token).isRevoked(false).build());
    }

}
