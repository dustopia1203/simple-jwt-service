package com.example.auth.service;

import org.springframework.stereotype.Service;

@Service
public interface TokenService {

    public void revokeAllTokensOfUser(String username);

    public void saveToken(String username, String token);

}
