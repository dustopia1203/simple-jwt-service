package com.example.auth.service.impl;

import com.example.auth.model.dto.LoginDto;
import com.example.auth.model.dto.AuthResponse;
import com.example.auth.model.dto.UserDto;
import com.example.auth.model.entity.User;
import com.example.auth.model.mapper.UserMapper;
import com.example.auth.repository.UserRepository;
import com.example.auth.security.CustomUserDetailsService;
import com.example.auth.security.jwt.JwtService;
import com.example.auth.service.AuthService;
import com.example.auth.service.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    private final TokenService tokenService;

    @Override
    public AuthResponse loginAndAuthenticate(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.username(), loginDto.password()));
        if (authentication.isAuthenticated()) {
            String jwt = jwtService.generateToken(userDetailsService.loadUserByUsername(loginDto.username()));
            String refreshToken = jwtService.generateRefreshToken(userDetailsService.loadUserByUsername(loginDto.username()));
            tokenService.revokeAllTokensOfUser(loginDto.username());
            tokenService.saveToken(loginDto.username(), jwt);
            return new AuthResponse(jwt, refreshToken);
        } else {
            throw new UsernameNotFoundException("Invalid credentials");
        }
    }

    @Override
    public UserDto registerNewUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username existed");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return userMapper.toUserDto(user);
    }

    @Override
    public void refreshJwtToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        String refreshToken = authHeader.substring(7);
        String username = jwtService.extractUsername(refreshToken);
        if (username != null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (userDetails != null && jwtService.isTokenValid(refreshToken)) {
                String accessToken = jwtService.generateToken(userDetails);
                tokenService.revokeAllTokensOfUser(username);
                tokenService.saveToken(username, accessToken);
                AuthResponse authResponse = new AuthResponse(accessToken, refreshToken);
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

}
