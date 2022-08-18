package com.example.dockerandmysql.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_validation")
public class userValidation {

    @Id
    int userId;
    private String userName;
    private String password;
}
