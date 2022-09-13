package com.epam.bookstore.entity;

import com.epam.bookstore.vo.BookVO;
import lombok.*;
import org.springframework.beans.BeanUtils;

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
    int totalCount;
    int sold;

    public Integer getNumberOfAvailableBooks(){
        return this.totalCount - this.sold;
    }

    public BookVO convertBookToBookVO(){
        BookVO bookVO = new BookVO();
        BeanUtils.copyProperties(this, bookVO);
        return bookVO;
    }

}
