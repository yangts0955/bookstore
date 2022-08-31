package com.epam.bookstore.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Book")
public class Book {

    @Id
    int id;
    String author;
    String title;
    String category;
    String price;
    //    @Column(name = "total_count")
    int totalCount;
    int sold;


}
