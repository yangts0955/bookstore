package com.example.dockerandmysql.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class userValidation {

    @Id
    int userId;
    private String user_name;
    private String password;
}
