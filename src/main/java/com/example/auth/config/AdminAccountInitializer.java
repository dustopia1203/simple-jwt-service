package com.example.auth.config;

import com.example.auth.model.dto.AuthResponse;
import com.example.auth.model.dto.LoginDto;
import com.example.auth.model.entity.User;
import com.example.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminAccountInitializer implements CommandLineRunner {

    private final AuthService authService;

    @Override
    public void run(String... args) throws Exception {
        User admin = User
                .builder()
                .username("admin")
                .password("123456")
                .role("ADMIN")
                .build();
        authService.registerNewUser(admin);
        AuthResponse authResponse = authService.loginAndAuthenticate(new LoginDto("admin", "123456"));
        System.out.println("Admin token: " + authResponse.token());
    }

}
