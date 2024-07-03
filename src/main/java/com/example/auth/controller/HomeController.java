package com.example.auth.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/home")
@Tag(
        name = "Home controller",
        description = "Home controller for home page"
)
public class HomeController {

    @GetMapping("")
    @Operation(
            description = "GET endpoint for app home page",
            summary = "App home page",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    )
            }
    )
    public String handleWelcome() {
        return "Welcome to home!";
    }

    @GetMapping("/admin")
    @Operation(
            description = "GET endpoint for admin home page",
            summary = "Admin home page",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthenticated/unauthorized",
                            responseCode = "403"
                    )
            }
    )
    public String handleAdminHome() {
        return "Welcome to ADMIN home!";
    }

    @GetMapping("/user")
    @Hidden
    public String handleUserHome() {
        return "Welcome to USER home!";
    }

}
