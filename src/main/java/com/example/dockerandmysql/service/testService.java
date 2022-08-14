package com.example.dockerandmysql.service;


import com.example.dockerandmysql.model.User;
import com.example.dockerandmysql.repository.testRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class testService {

    @Autowired
    private testRepository testRepository;

    public User getUser(Long id){
        return testRepository.findAllById(id);
    }

    public int AddTest(int a,int b){
        int result = a + b;
        return result;
    }
}
