package com.example.dockerandmysql.service;

import com.example.dockerandmysql.model.userValidation;
import com.example.dockerandmysql.repository.UserRepository;
import com.example.dockerandmysql.service.ServiceInterface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public userValidation getUserValidationById(int user_id) {
        return userRepository.findByUserId(user_id);
    }

}
