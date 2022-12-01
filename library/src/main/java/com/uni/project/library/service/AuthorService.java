package com.uni.project.library.service;

import com.uni.project.library.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuthorService {

    List<Author> findAllAuthors();

    Author findAuthorById(Long id);

    Author createAuthor(Author author);

    void updateAuthor(Author author);

    void deleteAuthor(Long id);

    Page<Author> findPaginated(Pageable pageable);

}
