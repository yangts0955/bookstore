package com.example.dockerandmysql.repository;

import com.example.dockerandmysql.model.userValidation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<userValidation, Integer> {

    userValidation findByUserId(Integer userId);

    userValidation findByUserName(String user_name);
}
