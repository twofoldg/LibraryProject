package com.uni.project.library.service;

import com.uni.project.library.entity.Book;
import com.uni.project.library.exception.AlreadyExistsExceptionCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAllBooks();

    List<Book> searchBooks(String keyword);

    Book findBookById(Long id);

    void createBook(Book book);

    void updateBook(Book book);

    void deleteBook(Long id);

    Page<Book> findPaginated(Pageable pageable);

    Optional<Book> findBookByIsbn(String isbn) throws AlreadyExistsExceptionCustom;
}
