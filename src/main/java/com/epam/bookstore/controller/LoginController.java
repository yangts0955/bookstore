package com.epam.bookstore.controller;

import com.epam.bookstore.entity.User;
import com.epam.bookstore.exception.CommonResult;
import com.epam.bookstore.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("hello")
    public CommonResult<String> hello(){
        return CommonResult.success("hello");
    }


    @PostMapping("login")
    public CommonResult<Boolean> login(@RequestBody User user) {
        return CommonResult.success(loginService.login(user));
    }

    @RequestMapping("logout")
    public CommonResult<Boolean> logout(){
        return CommonResult.success(loginService.logout());
    }
}
