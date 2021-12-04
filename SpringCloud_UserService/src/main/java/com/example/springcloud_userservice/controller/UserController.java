package com.example.springcloud_userservice.controller;

import com.example.springcloud_userservice.dto.UserDto;
import com.example.springcloud_userservice.service.UserService;
import com.example.springcloud_userservice.service.UserServiceImpl;
import com.example.springcloud_userservice.vo.RequestUser;
import com.example.springcloud_userservice.vo.ResponseUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {

    @GetMapping("/health_check")
    public String status() {
        return "it's working service";
    }

    private Environment env;

    @Autowired
    private UserServiceImpl userService;

    @Autowired // 생성자 주입
    public UserController(Environment env) {
        this.env = env;
    }

    @GetMapping("/welcome")
    public String welcome() {
        return env.getProperty("greeting.msg");
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser user) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = mapper.map(user, UserDto.class);

        Logger logger = LoggerFactory.getLogger(UserController.class);
        logger.info("userDto :  "  + userDto);

        userService.createUser(userDto);

        ResponseUser responseUser = mapper.map(userDto, ResponseUser.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }
}
