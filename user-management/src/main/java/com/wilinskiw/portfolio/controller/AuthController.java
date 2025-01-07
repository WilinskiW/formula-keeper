package com.wilinskiw.portfolio.controller;

import com.wilinskiw.portfolio.dto.UserInfoDto;
import com.wilinskiw.portfolio.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    //register user
    @PostMapping("/register")
    public void registerUser(@RequestBody UserInfoDto userRegister){
        authService.registerUser(userRegister);
    }

    //log in user
    @PostMapping("/login")
    public void loginUser(@RequestBody UserInfoDto userLogin){
        authService.loginUser(userLogin);
    }
}
