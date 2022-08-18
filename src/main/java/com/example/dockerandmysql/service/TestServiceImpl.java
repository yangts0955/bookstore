package com.example.dockerandmysql.service;


import com.example.dockerandmysql.model.User;
import com.example.dockerandmysql.repository.testRepository;
import com.example.dockerandmysql.service.ServiceInterface.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private testRepository testRepository;

    @Override
    public User getUser(Long id) {
        return testRepository.findAllById(id);
    }

    @Override
    public int AddTest(int a, int b) {
        int result = a + b;
        return result;
    }
}
