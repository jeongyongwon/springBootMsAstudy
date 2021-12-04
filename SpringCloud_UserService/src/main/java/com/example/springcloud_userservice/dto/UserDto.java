package com.example.springcloud_userservice.dto;

import lombok.Data;

import java.util.Date;

@Data //lombok 떄문에  getter setter 알아서 생성되잇음
public class UserDto {
    private String email;
    private String name;
    private String pwd;
    private String userId;
    private Date createAt;

    private String encryptedPwd;

}
