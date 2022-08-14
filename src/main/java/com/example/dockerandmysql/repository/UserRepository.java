package com.example.dockerandmysql.repository;

import com.example.dockerandmysql.model.userValidation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<userValidation, Integer> {

    userValidation findByUserId(Integer userId);
}
