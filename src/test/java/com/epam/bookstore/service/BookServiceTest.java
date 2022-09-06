package com.epam.bookstore.service;

import com.epam.bookstore.dto.BookDTO;
import com.epam.bookstore.repository.BookRepository;
import com.epam.bookstore.service.ServiceImpl.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.spy;

@SpringBootTest
class BookServiceTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookService bookService;

    @Test
    void addNewBook() {
        BookDTO bookDTO = BookDTO.builder()
                .category("test")
                .id(999)
                .count(20)
                .author("a-test")
                .price("30")
                .title("t-test")
                .build();
        bookService.addNewBook(bookDTO);
    }

    @Test
    void getBookById() {
    }

    @Test
    void getAllBooks() {
    }
}