package com.epam.bookstore.vo;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookVO {
    int id;
    String author;
    String title;
    String category;
    String price;
    int totalCount;
    int sold;
}
