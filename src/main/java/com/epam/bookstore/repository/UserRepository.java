package com.epam.bookstore.repository;

import com.epam.bookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUserId(Integer userId);

    Optional<User> findByUserName(String user_name);
}
