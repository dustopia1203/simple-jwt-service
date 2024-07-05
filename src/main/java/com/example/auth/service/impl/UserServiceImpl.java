package com.example.auth.service.impl;

import com.example.auth.model.dto.ChangePasswordRequest;
import com.example.auth.model.entity.User;
import com.example.auth.repository.UserRepository;
import com.example.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void changePassword(ChangePasswordRequest changePasswordRequest, Principal connectedUser) throws IllegalAccessException {
        String username = (String) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("User not found!");
        }
        if (!passwordEncoder.matches(changePasswordRequest.oldPassword(), user.getPassword())) {
            throw new IllegalAccessException("Wrong password");
        }
        if (!changePasswordRequest.newPassword().equals(changePasswordRequest.confirmNewPassword())) {
            throw new IllegalAccessException("Password are not the same");
        }
        user.setPassword(passwordEncoder.encode(changePasswordRequest.newPassword()));
        userRepository.save(user);
    }

}
