package com.example.dockerandmysql.repository;

import com.example.dockerandmysql.DTO.BookDTO;
import com.example.dockerandmysql.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    Optional<Book> findByTitleAndAuthor(String title, String author);


}
