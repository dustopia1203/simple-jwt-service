package com.example.auth.service.impl;

import com.example.auth.model.dto.LoginDto;
import com.example.auth.model.dto.UserDto;
import com.example.auth.model.entity.User;
import com.example.auth.model.mapper.UserMapper;
import com.example.auth.repository.UserRepository;
import com.example.auth.security.jwt.JwtService;
import com.example.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    @Override
    public String loginAndAuthenticate(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.username(), loginDto.password()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(userDetailsService.loadUserByUsername(loginDto.username()));
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

}
