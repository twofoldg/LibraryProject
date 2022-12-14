package com.uni.project.library.repository;

import com.uni.project.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.name LIKE %?1%" + " OR b.isbn LIKE %?1%" + " OR b.serialName LIKE %?1%")
    List<Book> search(String keyword);

    Optional<Book> findByIsbn(String isbn);
}
