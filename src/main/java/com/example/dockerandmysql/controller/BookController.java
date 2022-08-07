package com.example.dockerandmysql.controller;


import com.example.dockerandmysql.DTO.BookDTO;
import com.example.dockerandmysql.VO.BookVO;
import com.example.dockerandmysql.model.Book;
import com.example.dockerandmysql.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/add-new-book")
    public void addNewBook(@RequestBody BookDTO book){
        bookService.addNewBook(book);
    }

    @PostMapping("/add-book")
    public void addBook(@RequestBody BookDTO book){
        bookService.addNewBook(book);
    }

    @GetMapping("/book/{id}")
    public BookVO getBookById(@PathVariable int id){
        return bookService.getBookById(id);
    }

    @GetMapping("/book-list")
    public List<BookVO> getAllBooks(){
        return bookService.getAllBooks();
    }
}
