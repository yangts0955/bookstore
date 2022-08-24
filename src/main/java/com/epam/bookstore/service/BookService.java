package com.epam.bookstore.service;

import com.epam.bookstore.dto.BookDTO;
import com.epam.bookstore.exception.core.UnifyResponse;
import com.epam.bookstore.vo.BookVO;

import java.util.List;

public interface BookService {

    void addNewBook(BookDTO book);

    void addBook(BookDTO book);

    BookVO getBookById(int id);

    List<BookVO> getAllBooks();
}
