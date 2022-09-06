package com.epam.bookstore.service.ServiceImpl;

import com.epam.bookstore.dto.BookDTO;
import com.epam.bookstore.dto.SellDTO;
import com.epam.bookstore.exception.ApiException;
import com.epam.bookstore.exception.ResultCode;
import com.epam.bookstore.service.BookService;
import com.epam.bookstore.vo.BookVO;
import com.epam.bookstore.entity.Book;
import com.epam.bookstore.repository.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.xml.transform.Result;
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
        }, () -> {throw new ApiException(ResultCode.VALIDATE_FAILED);});
        return true;
    }

    @Override
    public BookVO getBookById(int id) {
        Optional<Book> book = bookRepository.findById(id);
        book.orElseThrow(() -> new ApiException(ResultCode.VALIDATE_FAILED));

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
        }).toList();
        return bookList;
    }

    @Override
    public Integer getNumberOfBooks(int id) {
        Optional<Book> book = bookRepository.findById(id);
        book.orElseThrow(() -> new ApiException(ResultCode.VALIDATE_FAILED));
        return book.get().getTotalCount() - book.get().getSold();
    }

    @Override
    public Boolean updateBook(int id, BookDTO bookDTO) {
        Optional<Book> existedBook = bookRepository.findById(id);
        existedBook.orElseThrow(() -> {throw new ApiException(ResultCode.VALIDATE_FAILED);});
        int bookDTOId = bookDTO.getId();
        if (bookDTOId != 0 && id != bookDTOId){
            throw new ApiException(ResultCode.VALIDATE_FAILED);
        }
        bookDTO.setId(id);
        Book book = bookDTO.convertBookDTOToBook();
        book.setSold(existedBook.get().getSold());
        bookRepository.save(book);
        return true;
    }

    @Override
    public Boolean sellABook(int id) {
        Optional<Book> existedBook = bookRepository.findById(id);
        existedBook.ifPresentOrElse(book -> {
            book.setSold(book.getSold() +1);
            //if no enough book can be sold
            if (book.getSold() > book.getTotalCount()){
                throw new ApiException(ResultCode.NO_ENOUGH_BOOK);
            }
            bookRepository.save(book);
        }, ()-> {throw new ApiException(ResultCode.VALIDATE_FAILED);});
        return true;
    }

    public Boolean sellABookWithCount(int id, int count) {
        Optional<Book> existedBook = bookRepository.findById(id);
        existedBook.ifPresentOrElse(book -> {
            book.setSold(book.getSold() + count);
            //if no enough book can be sold
            if (book.getSold() > book.getTotalCount()){
                throw new ApiException(ResultCode.NO_ENOUGH_BOOK);
            }
            bookRepository.save(book);
        }, ()-> {throw new ApiException(ResultCode.VALIDATE_FAILED);});
        return true;
    }

    @Override
    public Boolean sellBooks(List<SellDTO> sellDTOs){
        for (SellDTO sellDTO : sellDTOs){
            sellABookWithCount(sellDTO.getId(), sellDTO.getCount());
        }
        return true;
    };

    @Override
    public List<BookVO> getBooksByCategoryAndKeyword(String category, String keyword) {
        String likeKeyword = "%" + keyword + "%";
        List<Optional<Book>> books = bookRepository.findAllByCategoryAndKeyword(category, likeKeyword);
        List<BookVO> bookList = books.stream().map(book -> {
            BookVO bookVO = new BookVO();
            BeanUtils.copyProperties(book.get(), bookVO);
            return bookVO;
        }).toList();
        return bookList;
    }

    @Override
    public Integer getSoldNumberByCategoryAndKeyword(String category, String keyword) {
        int count = 0;
        List<BookVO> books = getBooksByCategoryAndKeyword(category, keyword);
        for (BookVO book : books){
            count += book.getSold();
        }
        return count;
    }


}
