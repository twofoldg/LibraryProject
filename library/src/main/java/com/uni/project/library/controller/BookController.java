package com.uni.project.library.controller;

import com.uni.project.library.entity.Author;
import com.uni.project.library.entity.Book;
import com.uni.project.library.entity.Category;
import com.uni.project.library.entity.Publisher;
import com.uni.project.library.exception.AlreadyExistsExceptionCustom;
import com.uni.project.library.service.AuthorService;
import com.uni.project.library.service.BookService;
import com.uni.project.library.service.CategoryService;
import com.uni.project.library.service.PublisherService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class BookController {
    final BookService bookService;
    final AuthorService authorService;
    final CategoryService categoryService;
    final PublisherService publisherService;

    public BookController(PublisherService publisherService, CategoryService categoryService, BookService bookService,
                          AuthorService authorService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.categoryService = categoryService;
        this.publisherService = publisherService;
    }

    @RequestMapping({ "/books", "/" })
    public String findAllBooks(Model model, @RequestParam("page") Optional<Integer> page,
                               @RequestParam("size") Optional<Integer> size) {

        Integer currentPage = page.orElse(1);
        Integer pageSize = size.orElse(5);

        Page<Book> bookPage = bookService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("books", bookPage);

        int totalPages = bookPage.getTotalPages();
        if (totalPages > 0) {
            var pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().toList();
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "list-books";
    }

    @RequestMapping({ "/booksIsbn", "/" })
    public String findAllBooksIsbn(Model model, @RequestParam("page") Optional<Integer> page,
                               @RequestParam("size") Optional<Integer> size) {

        Integer currentPage = page.orElse(1);
        Integer pageSize = size.orElse(5);

        Page<Book> bookPage = bookService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("books", bookPage);

        int totalPages = bookPage.getTotalPages();
        if (totalPages > 0) {
            var pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().toList();
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "list-books-isbn-error";
    }

    @RequestMapping("/searchBook")
    public String searchBook(@Param("keyword") String keyword, Model model) {

        model.addAttribute("books", bookService.searchBooks(keyword));
        model.addAttribute("keyword", keyword);
        return "list-books";
    }

    @RequestMapping("/book/{id}")
    public String findBookById(@PathVariable("id") Long id, Model model) {

        model.addAttribute("book", bookService.findBookById(id));

        return "list-book";
    }

    @GetMapping("/add")
    public String showCreateForm(Book book,
                                 Model model) {

        model.addAttribute("categories", categoryService.findAllCategories());
        model.addAttribute("authors", authorService.findAllAuthors());
        model.addAttribute("publishers", publisherService.findAllPublishers());
        return "add-book";
    }

    @RequestMapping("/add-book")
    public String createBook(Book book,
                             BindingResult result,
                             Model model) throws AlreadyExistsExceptionCustom {

        if (bookService.findBookByIsbn(book.getIsbn()).isPresent()) {
            result.addError(new ObjectError("isbn", "ISBN already exists"));
        }

        if (result.hasErrors()) {
            return "redirect:/booksIsbn";
        }

        bookService.createBook(book);
        model.addAttribute("book", bookService.findAllBooks());
        return "redirect:/books";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {

        model.addAttribute("book", bookService.findBookById(id));
        return "update-book";
    }

    @RequestMapping("/update-book/{id}")
    public String updateBook(@PathVariable("id") Long id, Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            book.setId(id);
            return "update-book";
        }

        bookService.updateBook(book);
        model.addAttribute("book", bookService.findAllBooks());
        return "redirect:/books";
    }

    @RequestMapping("/remove-book/{id}")
    public String deleteBook(@PathVariable("id") Long id, Model model) {
        bookService.deleteBook(id);

        model.addAttribute("book", bookService.findAllBooks());
        return "redirect:/books";
    }
}
