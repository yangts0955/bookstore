package com.epam.bookstore.service.ServiceImpl;

import com.epam.bookstore.config.jwt.JwtToken;
import com.epam.bookstore.entity.LoginUser;
import com.epam.bookstore.entity.User;
import com.epam.bookstore.exception.ApiException;
import com.epam.bookstore.exception.ResultCode;
import com.epam.bookstore.repository.UserRepository;
import com.epam.bookstore.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authentication)){
            throw new ApiException(ResultCode.FAILED);
        }
        Optional<User> existedUser = userRepository.findByUserName(user.getUserName());
        existedUser.orElseThrow(() -> {throw new ApiException(ResultCode.VALIDATE_FAILED);});
        return JwtToken.makeToken(existedUser.get().getUserId());
    }

    @Override
    public Boolean logout() {
        return true;
    }
}
