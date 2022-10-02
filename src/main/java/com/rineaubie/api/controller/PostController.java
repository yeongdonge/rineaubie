package com.rineaubie.api.controller;

import com.rineaubie.api.request.PostCreate;
import com.rineaubie.api.request.PostEdit;
import com.rineaubie.api.request.PostSearch;
import com.rineaubie.api.response.PostResponse;
import com.rineaubie.api.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/post/")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final HttpSession httpSession;

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

    // 글 등록, 글 단건 조회, 글 리스트 조회
    // CRUD -> Create, Read, Update, Delete

//    @GetMapping
//    public String index(Model model) {
//        SessionUser user = (SessionUser) httpSession.getAttribute("user");
//
//    }


    @PostMapping("/posts")
    public void post(@RequestBody @Valid PostCreate request) {
        request.validate();
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
    public List<PostResponse> getList(@ModelAttribute PostSearch postSearch) {
        return postService.getList(postSearch);
    }

    @PatchMapping("/posts/{postId}")
    public void edit(@PathVariable Long postId, @RequestBody @Valid PostEdit request) {
        postService.edit(postId, request);
    }

    @DeleteMapping("/posts/{postId}")
    public void delete(@PathVariable Long postId) {
        postService.delete(postId);
    }
}
