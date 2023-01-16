package com.example.springcloud_userservice.service;

import com.example.springcloud_userservice.dto.UserDto;
import com.example.springcloud_userservice.jpa.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto userDto);

    UserDto getUserByUserId(String userId);

    Iterable<UserEntity> getUserByAll();
}
