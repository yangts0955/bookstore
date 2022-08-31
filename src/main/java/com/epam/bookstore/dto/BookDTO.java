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
    String author;
    String title;
    String category;
    String price;
    int count;

    public Book convertBookDTOToBook(){
        return Book.builder()
                .author(this.getAuthor())
                .title(this.getTitle())
                .category(this.getCategory())
                .price(this.getPrice())
                .total_count(this.getCount())
                .build();
    }
}
