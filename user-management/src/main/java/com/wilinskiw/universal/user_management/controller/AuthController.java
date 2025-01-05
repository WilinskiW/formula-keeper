package com.wilinskiw.universal.user_management.controller;

import com.wilinskiw.universal.user_management.dto.UserInfoDto;
import com.wilinskiw.universal.user_management.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping( "/hello" )
    public String echo() {
        return "Hello World!";
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
