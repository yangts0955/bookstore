package com.example.dockerandmysql.service;

import com.example.dockerandmysql.model.userValidation;
import com.example.dockerandmysql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public userValidation getUserValidationById(int user_id){
        return userRepository.findByUserId(user_id);
    }

}
