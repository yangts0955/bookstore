package com.example.dockerandmysql.service;


import com.example.dockerandmysql.model.User;
import com.example.dockerandmysql.repository.testRepository;
import org.springframework.stereotype.Service;

@Service
public class testService {

    private final com.example.dockerandmysql.repository.testRepository testRepository;

    public testService(testRepository testRepository) {
        this.testRepository = testRepository;
    }

    public User getUser(Long id){
        return testRepository.findAllById(id);
    }
}
