package com.example.dockerandmysql.controller;


import com.example.dockerandmysql.dto.BookDTO;
import com.example.dockerandmysql.vo.BookVO;
import com.example.dockerandmysql.login.interceptor.ScopeLevel;
import com.example.dockerandmysql.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookServiceImpl bookService;

    @ScopeLevel(8)
    @PostMapping("/add-new-book")
    public void addNewBook(@RequestBody BookDTO book) {
        bookService.addNewBook(book);
    }

    @ScopeLevel(8)
    @PostMapping("/add-book")
    public void addBook(@RequestBody BookDTO book) {
        bookService.addNewBook(book);
    }

    @GetMapping("/book/{id}")
    public BookVO getBookById(@PathVariable int id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/book-list")
    public List<BookVO> getAllBooks() {
        return bookService.getAllBooks();
    }
}
