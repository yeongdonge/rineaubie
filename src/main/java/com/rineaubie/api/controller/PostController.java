package com.rineaubie.api.controller;

import com.rineaubie.api.domain.Post;
import com.rineaubie.api.request.PostCreate;
import com.rineaubie.api.response.PostResponse;
import com.rineaubie.api.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public void post(@RequestBody @Valid PostCreate request) {
        postService.write(request);
    }

    /**
     * /posts -> 글 전체 조회(검색 + 페이징)
     * /posts/{postId} -> 글 단건 조회
     */

    @GetMapping("/posts/{postId}")
    public PostResponse get(@PathVariable(name = "postId") Long id) {
        // Request 클래스
        // Response 클래스

        PostResponse response = postService.get(id);
        return response;
    }
}
