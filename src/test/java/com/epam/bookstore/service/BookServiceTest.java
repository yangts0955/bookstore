package com.epam.bookstore.service;

import com.epam.bookstore.dto.BookDTO;
import com.epam.bookstore.dto.SellDTO;
import com.epam.bookstore.entity.Book;
import com.epam.bookstore.repository.BookRepository;
import com.epam.bookstore.service.ServiceImpl.BookServiceImpl;
import com.epam.bookstore.vo.BookVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;


@SpringBootTest
class BookServiceTest {

    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookServiceImpl bookService;


    BookDTO mockBookDTO = BookDTO.builder()
            .category("c-test")
            .count(20)
            .author("a-test")
            .price("20")
            .title("t-test").build();

    @Test
    void addNewBook() {
        when(bookService.addNewBook(mockBookDTO)).thenReturn(true);
        Boolean updated = bookService.addNewBook(mockBookDTO);
        Assertions.assertTrue(updated);
    }

    @Test
    void addBook(){
        Book book =  mockBookDTO.convertBookDTOToBook();
        Optional<Book> optionalBook = Optional.ofNullable(book);
        when(bookRepository.findByTitleAndAuthor("t-test","a-test")).thenReturn(optionalBook);
        Boolean updated = bookService.addBook(mockBookDTO);
        Assertions.assertTrue(updated);
    }

    @Test
    void getBookById(){
        Optional<Book> optionalBook = Optional.ofNullable(mockBookDTO.convertBookDTOToBook());
        when(bookRepository.findById(1)).thenReturn(optionalBook);
        BookVO mockBookVO = optionalBook.get().convertBookToBookVO();
        Assertions.assertEquals(mockBookVO.getTitle(), bookService.getBookById(1).getTitle());
        Assertions.assertEquals(mockBookVO.getAuthor(), bookService.getBookById(1).getAuthor());
        Assertions.assertEquals(mockBookVO.getCategory(), bookService.getBookById(1).getCategory());
        Assertions.assertEquals(mockBookVO.getPrice(), bookService.getBookById(1).getPrice());
        Assertions.assertEquals(mockBookVO.getSold(), bookService.getBookById(1).getSold());

    }

    @Test
    void getAllBooks(){
        List<Book> list = new ArrayList<>();
        Book book = mockBookDTO.convertBookDTOToBook();
        list.add(book);
        when(bookRepository.findAll()).thenReturn(list);
        Assertions.assertEquals(book.convertBookToBookVO().getTitle(), bookService.getAllBooks().get(0).getTitle());
        Assertions.assertEquals(book.convertBookToBookVO().getAuthor(), bookService.getAllBooks().get(0).getAuthor());
    }

    @Test
    void getNumberOfBooks(){
        Optional<Book> optionalBook = Optional.ofNullable(mockBookDTO.convertBookDTOToBook());
        when(bookRepository.findById(1)).thenReturn(optionalBook);
        Assertions.assertEquals(20, bookService.getNumberOfBooks(1));
    }

    @Test
    void updateBook(){
        Optional<Book> optionalBook = Optional.ofNullable(mockBookDTO.convertBookDTOToBook());
        when(bookRepository.findById(1)).thenReturn(optionalBook);
        Assertions.assertTrue(bookService.updateBook(1, mockBookDTO));
    }

    @Test
    void sellABook(){
        Optional<Book> optionalBook = Optional.ofNullable(mockBookDTO.convertBookDTOToBook());
        when(bookRepository.findById(1)).thenReturn(optionalBook);
        Assertions.assertTrue(bookService.sellABook(1));
    }

    @Test
    void sellBooks(){
        List<SellDTO> list = new ArrayList<>();
        SellDTO sellDTO = new SellDTO(1, 5);
        list.add(sellDTO);

        Optional<Book> optionalBook = Optional.ofNullable(mockBookDTO.convertBookDTOToBook());
        when(bookRepository.findById(1)).thenReturn(optionalBook);

        Assertions.assertTrue(bookService.sellBooks(list));
    }

    @Test
    void getBooksByCategoryAndKeyword(){
        List<Optional<Book>> list = new ArrayList<>();
        Optional<Book> optionalBook = Optional.ofNullable(mockBookDTO.convertBookDTOToBook());
        list.add(optionalBook);
        when(bookRepository.findAllByCategoryAndKeyword("c-category","%test%")).thenReturn(list);

        List<BookVO> bookVOList = new ArrayList<>();
        BookVO bookVO = mockBookDTO.convertBookDTOToBook().convertBookToBookVO();
        bookVOList.add(bookVO);
        Assertions.assertEquals(bookVOList.get(0).getTitle(),
                bookService.getBooksByCategoryAndKeyword("c-category","test").get(0).getTitle());
        Assertions.assertEquals(bookVOList.get(0).getAuthor(),
                bookService.getBooksByCategoryAndKeyword("c-category","test").get(0).getAuthor());

        Assertions.assertEquals(0, bookService.getSoldNumberByCategoryAndKeyword("c-category","test"));
    }

}