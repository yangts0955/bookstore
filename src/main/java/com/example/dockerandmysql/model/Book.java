package com.example.dockerandmysql.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    int id;
    String author;
    String title;
    String category;
    String price;
//    @Column(name = "total_count")
    int total_count;
    int sold;



}
