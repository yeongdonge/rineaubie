package com.rineaubie.api.controller;

import com.rineaubie.api.request.PostCreate;
import com.rineaubie.api.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // Http Method
    // GET, POST, PUT, PATCH, DELETE, OPTIONS, HEAD, TRACE, CONNECT
    // 글 등록
    // POST Method

    // SSR -> jsp, thymeleaf, mustache,
        // -> html rendering
    // SPA
    // vue, nuxt
    // react, next
        // -> javascript + <-> API (JSON)

    @PostMapping("/posts")
    public Map<String, String> post(@RequestBody @Valid PostCreate request) {

        postService.write(request);
        return Map.of();
    }
}
