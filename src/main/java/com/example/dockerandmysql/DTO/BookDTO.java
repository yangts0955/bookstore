package com.example.dockerandmysql.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {
    String author;
    String title;
    String category;
    String price;
    int count;
}
