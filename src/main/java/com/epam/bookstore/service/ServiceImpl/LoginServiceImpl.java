package com.epam.bookstore.service.ServiceImpl;

import com.epam.bookstore.entity.LoginUser;
import com.epam.bookstore.entity.User;
import com.epam.bookstore.exception.ApiException;
import com.epam.bookstore.exception.ResultCode;
import com.epam.bookstore.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public Boolean login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authentication)){
            throw new ApiException(ResultCode.FAILED);
        }
        return true;
    }

    @Override
    public Boolean logout() {
        return true;
    }
}
