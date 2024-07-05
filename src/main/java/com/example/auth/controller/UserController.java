package com.example.auth.controller;

import com.example.auth.model.dto.ChangePasswordRequest;
import com.example.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest changePasswordRequest,
            Principal connectedUser
    ) throws IllegalAccessException {
        userService.changePassword(changePasswordRequest, connectedUser);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
