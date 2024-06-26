package com.example.auth.model.mapper;

import com.example.auth.model.dto.UserDto;
import com.example.auth.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toUserDto(User user) {
        return new UserDto(user.getUsername(), user.getPassword(), user.getRole());
    }

}
