package com.example.springcloud_userservice.service;

import com.example.springcloud_userservice.controller.UserController;
import com.example.springcloud_userservice.dto.UserDto;
import com.example.springcloud_userservice.jpa.UserEntity;
import com.example.springcloud_userservice.jpa.UserRepository;
import com.example.springcloud_userservice.service.UserService;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Override
    public UserDto createUser(UserDto userDto) {
        this.logger.info("????  :  ");
        userDto.setUserId(UUID.randomUUID().toString());

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEntity userEntity = mapper.map(userDto, UserEntity.class); // userDto를 UserEntity class 로 변경해주세요
        userEntity.setEncryptedPwd("encrypted_password");

        userRepository.save(userEntity);

        UserDto returnUserDto = mapper.map(userEntity, UserDto.class);

        return returnUserDto;
    }
}
