package com.example.auth.model.dto;

public record UserDto(
        String username,
        String password,
        String role
) {
}
