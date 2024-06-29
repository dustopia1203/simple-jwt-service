package com.example.auth.service;

import com.example.auth.model.dto.LoginDto;
import com.example.auth.model.dto.AuthResponse;
import com.example.auth.model.dto.UserDto;
import com.example.auth.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface AuthService {

    public AuthResponse loginAndAuthenticate(LoginDto loginDto);

    public UserDto registerNewUser(User user);

    public void refreshJwtToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException;

}
