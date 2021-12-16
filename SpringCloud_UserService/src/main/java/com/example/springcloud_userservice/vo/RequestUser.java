package com.example.springcloud_userservice.vo;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class RequestUser {

    @NotNull(message = "email cannot null")
    @Size(min = 2, message = "email up 2char")
    @Email
    private String email;

    @NotNull(message = "Name cannot null")
    @Size(min = 2, message = "Name not be less than two characters")
    private String name;

    @NotNull(message = "password cannot null")
    @Size(min = 8, message = "pwd 8char")
    private String password;

}
