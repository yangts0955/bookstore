package com.epam.bookstore.dto;

import com.epam.bookstore.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class BookDTO {

    int id;
    String author;
    String title;
    String category;
    String price;
    int count;

    public Book convertBookDTOToBook(){
        return Book.builder()
                .id(this.id)
                .author(this.getAuthor())
                .title(this.getTitle())
                .category(this.getCategory())
                .price(this.getPrice())
                .totalCount(this.getCount())
                .build();
    }
}
