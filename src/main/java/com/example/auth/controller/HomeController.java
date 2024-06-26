package com.example.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/home")
public class HomeController {

    @GetMapping("")
    public String handleWelcome() {
        return "Welcome to home!";
    }

    @GetMapping("/admin")
    public String handleAdminHome() {
        return "Welcome to ADMIN home!";
    }

    @GetMapping("/user")
    public String handleUserHome() {
        return "Welcome to USER home!";
    }

}
