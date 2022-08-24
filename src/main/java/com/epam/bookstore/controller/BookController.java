package com.epam.bookstore.controller;


import com.epam.bookstore.config.interceptor.ScopeLevel;
import com.epam.bookstore.exception.core.UnifyResponse;
import com.epam.bookstore.service.ServiceImpl.BookServiceImpl;
import com.epam.bookstore.vo.BookVO;
import com.epam.bookstore.dto.BookDTO;
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
        UnifyResponse.createSuccess(0);
    }

    @ScopeLevel(8)
    @PostMapping("/add-book")
    public void addBook(@RequestBody BookDTO book) {
        bookService.addBook(book);
        UnifyResponse.createSuccess(0);
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
