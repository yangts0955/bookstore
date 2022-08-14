package com.example.dockerandmysql.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookDTO {
    String author;
    String title;
    String category;
    String price;
    int count;
}
