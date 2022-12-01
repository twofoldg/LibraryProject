package com.uni.project.library.repository;

import com.uni.project.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByName(String name);

    Optional<Book> findByAuthor(String author);
}
