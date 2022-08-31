package com.epam.bookstore.service;

import com.epam.bookstore.dto.BookDTO;
import com.epam.bookstore.vo.BookVO;

import java.util.List;

public interface BookService {

    Boolean addNewBook(BookDTO book);

    Boolean addBook(BookDTO book);

    BookVO getBookById(int id);

    List<BookVO> getAllBooks();
}
