package com.epam.bookstore.service.ServiceImpl;

import com.epam.bookstore.entity.User;
import com.epam.bookstore.repository.UserRepository;
import com.epam.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserValidationById(int user_id) {
        return userRepository.findByUserId(user_id);
    }

}
