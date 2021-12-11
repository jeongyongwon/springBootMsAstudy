package com.example.springcloud_userservice.service;

import com.example.springcloud_userservice.dto.UserDto;
import com.example.springcloud_userservice.jpa.UserEntity;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto getUserByUserId(String userId);

    Iterable<UserEntity> getUserByAll();
}
