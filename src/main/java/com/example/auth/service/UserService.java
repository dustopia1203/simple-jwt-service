package com.example.auth.service;

import com.example.auth.model.dto.ChangePasswordRequest;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public interface UserService {

    public void changePassword(ChangePasswordRequest changePasswordRequest, Principal connectedUser) throws IllegalAccessException;

}
