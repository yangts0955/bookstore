package com.example.dockerandmysql.service.ServiceInterface;

import com.example.dockerandmysql.model.userValidation;

public interface UserService {

    userValidation getUserValidationById(int user_id);

}
