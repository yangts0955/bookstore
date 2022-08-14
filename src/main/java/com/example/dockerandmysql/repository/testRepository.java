package com.example.dockerandmysql.repository;

import com.example.dockerandmysql.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface testRepository extends JpaRepository<User,Long> {

    User findAllById(Long Id);

}
