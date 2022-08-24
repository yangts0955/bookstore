package com.epam.bookstore.controller;

import com.epam.bookstore.entity.User;
import com.epam.bookstore.service.ServiceImpl.LoginServiceImpl;
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
    public String login(@RequestBody User user) {
        return loginService.verifyUserPassword(user);
    }
}
