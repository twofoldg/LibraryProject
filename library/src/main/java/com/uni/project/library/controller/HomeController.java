package com.uni.project.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @GetMapping(value = {"/login" , "/"})
    public String login() {
        return "login";
    }

    @RequestMapping(value = {"/index"})
    public String index() {
        return "list-books";
    }
}
