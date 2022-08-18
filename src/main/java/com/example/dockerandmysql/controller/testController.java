package com.example.dockerandmysql.controller;

import com.example.dockerandmysql.model.User;
import com.example.dockerandmysql.service.TestServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class testController {

    final TestServiceImpl testService;


    public testController(TestServiceImpl testService) {
        this.testService = testService;
    }

    @GetMapping("")
    public String test() {
        return "connected";
    }

    @GetMapping("/id/{id}")
    public User getUserById(@PathVariable Long id) {
        return testService.getUser(id);
    }
}
