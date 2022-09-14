package com.epam.bookstore.model;

import com.epam.bookstore.entity.Book;
import com.epam.bookstore.vo.BookVO;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.RepresentationModel;

public class BookModel extends RepresentationModel<BookModel> {

    private final BookVO content;

    @JsonCreator
    public BookModel(@JsonProperty("content") BookVO content){
        this.content = content;
    }

    public BookVO getContent(){
        return content;
    }
}
