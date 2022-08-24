package com.epam.bookstore.service;

import com.epam.bookstore.dto.BookDTO;
import com.epam.bookstore.service.ServiceImpl.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.spy;

@SpringBootTest
class BookServiceTest {

    @Spy
    private BookServiceImpl bookService = Mockito.spy(new BookServiceImpl());

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