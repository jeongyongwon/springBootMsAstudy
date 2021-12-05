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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder; //단순히 autowired 일때 에러라인 뜸 => Bean의 주입 안햇기 때문에 => 그래서 초기 기동 클래스에 등록

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Override
    public UserDto createUser(UserDto userDto) {
        this.logger.info("????  :  ");
        userDto.setUserId(UUID.randomUUID().toString());

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEntity userEntity = mapper.map(userDto, UserEntity.class); // userDto를 UserEntity class 로 변경해주세요
        userEntity.setEncryptedPwd(passwordEncoder.encode(userDto.getPwd()));

        userRepository.save(userEntity);

        UserDto returnUserDto = mapper.map(userEntity, UserDto.class);

        return returnUserDto;
    }
}
