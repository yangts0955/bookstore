package com.example.dockerandmysql.VO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookVO {
    int id;
    String author;
    String title;
    String category;
    String price;
    int total_count;
}
