package com.rineaubie.api.controller;

import com.rineaubie.api.request.PostCreate;
import com.rineaubie.api.response.PostResponse;
import com.rineaubie.api.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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


    // 조회 API
    // 단건 조회 API (1개의 Post를 가져오는 기능)

    @GetMapping("/posts/{postId}")
    public PostResponse get(@PathVariable Long postId) {
        // Request 클래스
        // Response 클래스
        return postService.get(postId);
    }

    // 조회 API
    // 여러개 조회 API
    // /posts

    @GetMapping("/posts")
    public List<PostResponse> getList(Pageable pageable) {
        return postService.getList(pageable);
    }
}
