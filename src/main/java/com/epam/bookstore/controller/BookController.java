package com.epam.bookstore.controller;


import com.epam.bookstore.config.interceptor.ScopeLevel;
import com.epam.bookstore.dto.SellDTO;
import com.epam.bookstore.exception.CommonResult;
import com.epam.bookstore.model.BookModel;
import com.epam.bookstore.service.BookService;
import com.epam.bookstore.service.ServiceImpl.BookServiceImpl;
import com.epam.bookstore.vo.BookVO;
import com.epam.bookstore.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
    public CommonResult<BookModel> getBookById(@PathVariable int id) {
        BookModel bookModel = new BookModel(bookService.getBookById(id));
        bookModel.add(linkTo(methodOn(BookController.class).getBookById(id)).withSelfRel());
        return CommonResult.success(bookModel);
    }

    @GetMapping("/book-list")
    public CommonResult<CollectionModel<BookVO>> getAllBooks() {
        CollectionModel<BookVO> model = CollectionModel.of(bookService.getAllBooks());
        model.add(linkTo(methodOn(BookController.class).getAllBooks()).withSelfRel());
        return CommonResult.success(model);
    }

    @GetMapping("/number-of-books/{id}")
    public CommonResult<Integer> getNumberOfBooks(@PathVariable int id){
        return CommonResult.success(bookService.getNumberOfBooks(id));
    }

    @PutMapping("books/{id}")
    public CommonResult<Boolean> updateBook(@PathVariable int id, @RequestBody BookDTO bookDTO){
        return CommonResult.success(bookService.updateBook(id, bookDTO));
    }

    @ScopeLevel(8)
    @PutMapping("sell-book/{id}")
    public CommonResult<Boolean> sellBook(@PathVariable int id){
        return CommonResult.success(bookService.sellABook(id));
    }

    @ScopeLevel(8)
    @PutMapping("sell-books")
    public CommonResult<Boolean> sellBooks(@RequestBody List<SellDTO> sellDTOs){
        return CommonResult.success(bookService.sellBooks(sellDTOs));
    }

    @GetMapping("books")
    public CommonResult<CollectionModel<BookVO>> getBooksByCategoryAndKeyword(String category, String keyword){
        CollectionModel<BookVO> model = CollectionModel.of(bookService.getBooksByCategoryAndKeyword(category,keyword));
        model.add(linkTo(methodOn(BookController.class).getBooksByCategoryAndKeyword(category, keyword)).withSelfRel());
        return CommonResult.success(model);
    }

    @GetMapping("number-of-books")
    public CommonResult<Integer> getSoldNumberByCategoryAndKeyword(String category, String keyword){
        return CommonResult.success(bookService.getSoldNumberByCategoryAndKeyword(category, keyword));
    }
}
