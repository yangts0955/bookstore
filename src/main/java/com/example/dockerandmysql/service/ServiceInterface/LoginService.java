package com.example.dockerandmysql.service.ServiceInterface;

import com.example.dockerandmysql.model.userValidation;

public interface LoginService {

    String verifyUserPassword(userValidation userValidation);

}
