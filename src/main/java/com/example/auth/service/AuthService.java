package com.example.auth.service;

import com.example.auth.model.dto.LoginDto;
import com.example.auth.model.dto.UserDto;
import com.example.auth.model.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    public String loginAndAuthenticate(LoginDto loginDto);

    UserDto registerNewUser(User user);
}
