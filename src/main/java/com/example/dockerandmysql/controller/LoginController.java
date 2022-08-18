package com.example.dockerandmysql.controller;

import com.example.dockerandmysql.model.userValidation;
import com.example.dockerandmysql.service.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginServiceImpl loginService;


    @PostMapping("/")
    public String login(@RequestBody userValidation user) {
        return loginService.verifyUserPassword(user);
    }
}
