package com.epam.bookstore.service;

import com.epam.bookstore.entity.User;

public interface LoginService {

    Boolean login(User user);

    Boolean logout();

}
