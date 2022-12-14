package com.uni.project.library.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.io.Serial;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Author extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 6682148315095000762L;

    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @ManyToMany(mappedBy = "author")
    private Set<Book> books = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
