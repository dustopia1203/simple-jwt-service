package com.example.auth.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthResponse(

        @JsonProperty("access_token")
        String token,

        @JsonProperty("refresh_token")
        String refreshToken
) {
}
