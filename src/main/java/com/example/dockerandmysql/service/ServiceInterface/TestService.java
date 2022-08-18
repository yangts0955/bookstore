package com.example.dockerandmysql.service.ServiceInterface;

import com.example.dockerandmysql.model.User;

public interface TestService {

    User getUser(Long id);

    int AddTest (int a,int b);
}
