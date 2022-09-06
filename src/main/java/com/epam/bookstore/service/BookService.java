package com.epam.bookstore.service;

import com.epam.bookstore.dto.BookDTO;
import com.epam.bookstore.dto.SellDTO;
import com.epam.bookstore.entity.Book;
import com.epam.bookstore.vo.BookVO;

import java.util.List;

public interface BookService {

    Boolean addNewBook(BookDTO book);

    Boolean addBook(BookDTO book);

    BookVO getBookById(int id);

    List<BookVO> getAllBooks();

    Integer getNumberOfBooks(int id);

    Boolean updateBook(int id, BookDTO bookDTO);

    Boolean sellABook(int id);

    Boolean sellBooks(List<SellDTO> sellDTOs);

    List<BookVO> getBooksByCategoryAndKeyword(String category, String keyword);

    Integer getSoldNumberByCategoryAndKeyword(String category, String keyword);

}
