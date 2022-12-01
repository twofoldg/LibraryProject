package com.uni.project.library.controller;

import com.uni.project.library.entity.Author;
import com.uni.project.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class AuthorController {

    final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @RequestMapping("/authors")
    public String findAllAuthors(Model model,
                                 @RequestParam("page") Optional<Integer> page,
                                 @RequestParam("size") Optional<Integer> size) {

        Integer currentPage = page.orElse(1);
        Integer pageSize = size.orElse(5);
        Page<Author> bookPage = authorService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("authors", bookPage);

        int totalPages = bookPage.getTotalPages();

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().toList();
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "list-authors";
    }

    @RequestMapping("/author/{id}")
    public String findAuthorById(@PathVariable("id") Long id,
                                 Model model) {

        model.addAttribute("author", authorService.findAuthorById(id));
        return "list-author";
    }

    @GetMapping("/addAuthor")
    public String showCreateForm(Author author) {
        return "add-author";
    }

    @RequestMapping("/add-author")
    public String createAuthor(Author author, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-author";
        }

        authorService.createAuthor(author);
        model.addAttribute("author", authorService.findAllAuthors());
        return "redirect:/authors";
    }

    @GetMapping("/updateAuthor/{id}")
    public String showUpdateForm(@PathVariable("id") Long id,
                                 Model model
    ) {

        model.addAttribute("author", authorService.findAuthorById(id));
        return "update-author";
    }

    @RequestMapping("/update-author/{id}")
    public String updateAuthor(@PathVariable("id") Long id,
                               Author author,
                               BindingResult result,
                               Model model
    ) {

        if (result.hasErrors()) {
            author.setId(id);
            return "update-author";
        }

        authorService.updateAuthor(author);
        model.addAttribute("author", authorService.findAllAuthors());
        return "redirect:/authors";
    }

    @RequestMapping("/remove-author/{id}")
    public String deleteAuthor(@PathVariable("id") Long id,
                               Model model
    ) {
        authorService.deleteAuthor(id);

        model.addAttribute("author", authorService.findAllAuthors());
        return "redirect:/authors";
    }
}
