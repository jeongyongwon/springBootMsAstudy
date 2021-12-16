package com.example.springcloud_userservice.dto;

import com.example.springcloud_userservice.vo.ResponseOrder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data //lombok 떄문에  getter setter 알아서 생성되잇음
public class UserDto {
    private String email;
    private String name;
    private String password;
    private String userId;
    private Date createAt;

    private String encryptedPwd;

    private List<ResponseOrder> orders;

}
