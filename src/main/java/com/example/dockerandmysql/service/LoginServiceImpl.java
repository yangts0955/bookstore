package com.example.dockerandmysql.service;

import com.example.dockerandmysql.exception.NotFoundException;
import com.example.dockerandmysql.exception.UnAuthenticateException;
import com.example.dockerandmysql.login.jwt.JwtToken;
import com.example.dockerandmysql.model.userValidation;
import com.example.dockerandmysql.repository.UserRepository;
import com.example.dockerandmysql.service.ServiceInterface.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public String verifyUserPassword(userValidation userValidation){
        userValidation user = userRepository.findByUserName(userValidation.getUserName());
        if (user == null){
            throw new NotFoundException(10003);
        }
        if (user.getPassword().equals(userValidation.getPassword())){
            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!不是每一个用户登录都新建token
            //获取token
            return JwtToken.makeToken(user.getUserId());
        }
        throw new UnAuthenticateException(10005);
    }
}
