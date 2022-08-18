package com.example.dockerandmysql.service;

import com.example.dockerandmysql.dto.BookDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.spy;

@SpringBootTest
class BookServiceTest {

    @Spy
    private BookServiceImpl bookService = spy(new BookServiceImpl());

    @Test
    void addNewBook() {
        BookDTO bookDTO = new BookDTO("a", "t", "c", "9", 2);
        bookService.addNewBook(bookDTO);
    }

    @Test
    void getBookById() {
    }

    @Test
    void getAllBooks() {
    }
}