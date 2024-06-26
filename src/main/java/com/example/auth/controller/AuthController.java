package com.example.auth.controller;

import com.example.auth.model.dto.LoginDto;
import com.example.auth.model.dto.UserDto;
import com.example.auth.model.entity.User;
import com.example.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> loginAndAuthenticate(
            @RequestBody LoginDto loginDto
    ) {
        String token = authService.loginAndAuthenticate(loginDto);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @PostMapping("register")
    public ResponseEntity<UserDto> registerNewUser(
            @RequestBody User user
    ) {
        UserDto userDto = authService.registerNewUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }

}
