package com.example.dockerandmysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class DockerAndMySqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(DockerAndMySqlApplication.class, args);
    }

}
