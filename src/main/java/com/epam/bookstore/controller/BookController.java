package com.epam.bookstore.controller;


import com.epam.bookstore.config.interceptor.ScopeLevel;
import com.epam.bookstore.exception.CommonResult;
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
    public CommonResult<Boolean> addNewBook(@RequestBody BookDTO book) {
        Boolean result = bookService.addNewBook(book);
        return CommonResult.success(result);
    }

    @ScopeLevel(8)
    @PostMapping("/add-book")
    public CommonResult<Boolean> addBook(@RequestBody BookDTO book) {
        Boolean result = bookService.addBook(book);
        return CommonResult.success(result);
    }

    @GetMapping("/book/{id}")
    public CommonResult<BookVO> getBookById(@PathVariable int id) {
        return CommonResult.success(bookService.getBookById(id));
    }

    @GetMapping("/book-list")
    public CommonResult<List<BookVO>> getAllBooks() {
        return CommonResult.success(bookService.getAllBooks());
    }
}
