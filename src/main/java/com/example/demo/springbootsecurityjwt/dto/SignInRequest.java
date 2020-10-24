package com.example.demo.springbootsecurityjwt.dto;

import lombok.Value;

@Value
public class SignInRequest {
    String username;
    String password;
}
