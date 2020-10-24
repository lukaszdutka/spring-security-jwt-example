package com.example.demo.springbootsecurityjwt;

import com.example.demo.springbootsecurityjwt.dto.SignInRequest;
import com.example.demo.springbootsecurityjwt.dto.SignInResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloWorldController {
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    public HelloWorldController(AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    @RequestMapping("/hello")
    public String hello() {
        return "Hello world";
    }

    @PostMapping("/signin")
    public SignInResponse signIn(@RequestBody SignInRequest signInRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));
            return jwtProvider.createToken(signInRequest.getUsername());
        } catch (AuthenticationException e) {
            System.out.println("Log in failed for user, " + signInRequest.getUsername());
        }
        return null;
    }
}