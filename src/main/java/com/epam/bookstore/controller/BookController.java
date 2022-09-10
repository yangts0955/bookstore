package com.epam.bookstore.controller;


import com.epam.bookstore.config.interceptor.ScopeLevel;
import com.epam.bookstore.dto.SellDTO;
import com.epam.bookstore.exception.CommonResult;
import com.epam.bookstore.service.BookService;
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
    private BookService bookService;

    @ScopeLevel(8)
    @PostMapping("/add-new-book")
    public CommonResult<Boolean> addNewBook(@RequestBody BookDTO book) {
        Boolean result = bookService.addNewBook(book);
        return CommonResult.success(result);
    }

    @ScopeLevel(8)
    @PostMapping("/add-book")
    public CommonResult<Boolean> addBook(@RequestBody BookDTO bookDTO) {
        Boolean result = bookService.addBook(bookDTO);
        return CommonResult.success(result);
    }

    @GetMapping("/book/{id}")
    public CommonResult<BookVO> getBookById(@PathVariable int id) {
        return CommonResult.success(bookService.getBookById(id));
    }

    @ScopeLevel(8)
    @GetMapping("/book-list")
    public CommonResult<List<BookVO>> getAllBooks() {
        return CommonResult.success(bookService.getAllBooks());
    }

    @GetMapping("/number-of-books/{id}")
    public CommonResult<Integer> getNumberOfBooks(@PathVariable int id){
        return CommonResult.success(bookService.getNumberOfBooks(id));
    }

    @PutMapping("books/{id}")
    public CommonResult<Boolean> updateBook(@PathVariable int id, @RequestBody BookDTO bookDTO){
        return CommonResult.success(bookService.updateBook(id, bookDTO));
    }

    @PutMapping("sell-book/{id}")
    public CommonResult<Boolean> sellBook(@PathVariable int id){
        return CommonResult.success(bookService.sellABook(id));
    }

    @PutMapping("sell-books")
    public CommonResult<Boolean> sellBooks(@RequestBody List<SellDTO> sellDTOs){
        return CommonResult.success(bookService.sellBooks(sellDTOs));
    }

    @GetMapping("books")
    public CommonResult<List<BookVO>> getBooksByCategoryAndKeyword(String category, String keyword){
        return CommonResult.success(bookService.getBooksByCategoryAndKeyword(category, keyword));
    }

    @GetMapping("number-of-books")
    public CommonResult<Integer> getSoldNumberByCategoryAndKeyword(String category, String keyword){
        return CommonResult.success(bookService.getSoldNumberByCategoryAndKeyword(category, keyword));
    }
}
