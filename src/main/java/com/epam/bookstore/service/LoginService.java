package com.epam.bookstore.service;

import com.epam.bookstore.entity.User;

public interface LoginService {

    String verifyUserPassword(User userValidation);

}
