package com.example.auth.controller;

import com.example.auth.model.dto.LoginDto;
import com.example.auth.model.dto.AuthResponse;
import com.example.auth.model.dto.UserDto;
import com.example.auth.model.entity.User;
import com.example.auth.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginAndAuthenticate(
            @RequestBody LoginDto loginDto
    ) {
      AuthResponse authResponse = authService.loginAndAuthenticate(loginDto);
        return ResponseEntity.status(HttpStatus.OK).body(authResponse);
    }

    @PostMapping("register")
    public ResponseEntity<UserDto> registerNewUser(
            @RequestBody User user
    ) {
        UserDto userDto = authService.registerNewUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }

    @PostMapping("refresh-token")
    public void refreshJwtToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        authService.refreshJwtToken(request, response);
    }

}
