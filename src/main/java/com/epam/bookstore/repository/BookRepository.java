package com.epam.bookstore.repository;

import com.epam.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    Optional<Book> findByTitleAndAuthor(String title, String author);

    @Query("select b from Book b where b.category = :category and (b.title like :keyword or b.author like :keyword)")
    List<Optional<Book>> findAllByCategoryAndKeyword(String category, String keyword);


}
