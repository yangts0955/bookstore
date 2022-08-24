package com.epam.bookstore.service.ServiceImpl;

import com.epam.bookstore.entity.User;
import com.epam.bookstore.exception.NotFoundException;
import com.epam.bookstore.exception.UnAuthenticateException;
import com.epam.bookstore.config.jwt.JwtToken;
import com.epam.bookstore.repository.UserRepository;
import com.epam.bookstore.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public String verifyUserPassword(User userValidation){
        User user = userRepository.findByUserName(userValidation.getUserName());
        if (user == null){
            throw new NotFoundException(10003);
        }
        if (user.getPassword().equals(userValidation.getPassword())){
            return JwtToken.makeToken(user.getUserId());
        }
        throw new UnAuthenticateException(10005);
    }
}
