package com.epam.bookstore.service.ServiceImpl;

import com.epam.bookstore.dto.BookDTO;
import com.epam.bookstore.exception.ApiException;
import com.epam.bookstore.exception.ResultCode;
import com.epam.bookstore.service.BookService;
import com.epam.bookstore.vo.BookVO;
import com.epam.bookstore.entity.Book;
import com.epam.bookstore.repository.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Boolean addNewBook(BookDTO bookDTO) {
            Book newBook = bookDTO.convertBookDTOToBook();
            if (newBook == null){
                throw new ApiException(ResultCode.FAILED);
            }
            bookRepository.save(newBook);
            return true;
    }

    @Override
    public Boolean addBook(BookDTO bookDTO) {
        Optional<Book> existedBook = bookRepository.findByTitleAndAuthor(bookDTO.getTitle(), bookDTO.getAuthor());
        existedBook.ifPresentOrElse(b -> {
            //add count to existed book's total count
            b.setTotalCount(b.getTotalCount() + bookDTO.getCount());
            bookRepository.save(b);
        }, () -> {throw new ApiException(ResultCode.FAILED);});
        return true;
    }

    @Override
    public BookVO getBookById(int id) {
        Optional<Book> book = bookRepository.findById(id);
        book.orElseThrow(() -> new ApiException(ResultCode.FAILED));

        BookVO bookVO = new BookVO();
        //copy properties from finding book to a new book vo which is needed to return
        book.ifPresent(b -> {
            BeanUtils.copyProperties(b, bookVO);
        });
        return bookVO;
    }

    @Override
    public List<BookVO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookVO> bookList = books.stream().map(book -> {
            BookVO bookVO = new BookVO();
            BeanUtils.copyProperties(book, bookVO);
            return bookVO;
        }).collect(Collectors.toList());
        return bookList;
    }
}
