package com.example.springcloud_userservice.service;

import com.example.springcloud_userservice.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);
}
