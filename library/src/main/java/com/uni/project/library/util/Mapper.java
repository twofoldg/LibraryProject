package com.uni.project.library.util;

import com.uni.project.library.entity.Author;
import com.uni.project.library.entity.Book;
import com.uni.project.library.entity.Category;
import com.uni.project.library.entity.Publisher;
import com.uni.project.library.record.AuthorRecord;
import com.uni.project.library.record.BookRecord;
import com.uni.project.library.record.CategoryRecord;
import com.uni.project.library.record.PublisherRecord;

import java.util.List;

public class Mapper {

    public static List<BookRecord> bookModelToPojo(List<Book> books) {

        return books.stream().map(pojo ->
                new BookRecord(
                pojo.getId(),
                pojo.getIsbn(),
                pojo.getName(),
                pojo.getSerialName(),
                pojo.getDescription()
        )).toList();
    }

    public static List<AuthorRecord> authorModelToPojo(List<Author> authors) {

        return authors.stream().map(pojo -> new AuthorRecord(
                pojo.getId(),
                pojo.getName()
        )).toList();

    }

    public static List<CategoryRecord> categoryModelToPojo(List<Category> categories) {

        return categories.stream().map(pojo -> new CategoryRecord(
                pojo.getId(),
                pojo.getName()
        )).toList();

    }

    public static List<PublisherRecord> publisherModelToPojo(List<Publisher> publishers) {

        return publishers.stream().map(pojo -> new PublisherRecord(
                pojo.getId(),
                pojo.getName()
        )).toList();
    }
}