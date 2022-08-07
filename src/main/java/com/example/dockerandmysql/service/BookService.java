package com.example.dockerandmysql.service;

import com.example.dockerandmysql.DTO.BookDTO;
import com.example.dockerandmysql.VO.BookVO;
import com.example.dockerandmysql.model.Book;
import com.example.dockerandmysql.repository.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public void addNewBook(BookDTO book){
        Optional<Book> existedBook= bookRepository.findByTitleAndAuthor(book.getTitle(), book.getAuthor());
        existedBook.ifPresentOrElse(b -> {
            //add count to existed book's total count
            b.setTotal_count(b.getTotal_count() + book.getCount());
            bookRepository.save(b);
        },() -> {
            //when book does not exist
            Book newBook = Book.builder()
                    .author(book.getAuthor())
                    .title(book.getTitle())
                    .category(book.getCategory())
                    .price(book.getPrice())
                    .total_count(book.getCount())
                    .build();

            bookRepository.save(newBook);
        });
    }

    public BookVO getBookById(int id){
        Optional<Book> book = bookRepository.findById(id);
        book.orElseThrow(() -> new RuntimeException("book not found"));

        BookVO bookVO = new BookVO();
        //copy properties from finding book to a new book vo which is needed to return
        book.ifPresent(b -> {
            BeanUtils.copyProperties(b,bookVO);
        });
        return bookVO;
    }

    public List<BookVO> getAllBooks(){
        List<Book> books = bookRepository.findAll();
        List<BookVO> book_list= books.stream().map(book -> {
            BookVO bookVO =new BookVO();
            BeanUtils.copyProperties(book, bookVO);
            return bookVO;
        }).collect(Collectors.toList());
        return book_list;
    }
}
