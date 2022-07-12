package com.rineaubie.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    // SSR -> jsp, thymeleaf, mustache,
        // -> html rendering
    // SPA
    // vue, nuxt
    // react, next
        // -> javascript + <-> API (JSON)

    @GetMapping("/posts")
    public String get() {
        return "Hello";
    }
}
