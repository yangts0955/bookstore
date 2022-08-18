package com.example.dockerandmysql.service.ServiceInterface;

import com.example.dockerandmysql.dto.BookDTO;
import com.example.dockerandmysql.vo.BookVO;

import java.util.List;

public interface BookService {

    void addNewBook(BookDTO book);

    BookVO getBookById(int id);

    List<BookVO> getAllBooks();
}
