package com.rineaubie.api.service;

import com.rineaubie.api.domain.Post;
import com.rineaubie.api.repository.PostRepository;
import com.rineaubie.api.request.PostCreate;
import com.rineaubie.api.response.PostResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    public void init() {
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("글 작성")
    void test1() {
        // given
        PostCreate postCreate = PostCreate.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        // when
        postService.write(postCreate);

        // then
        assertEquals(1L, postRepository.count());
        Post post = postRepository.findAll().get(0);
        assertEquals("제목입니다.", post.getTitle());
        assertEquals("내용입니다.", post.getContent());
    }

    @Test
    @DisplayName("글 1개 조회")
    void test2() {
        //given
        Post post = Post.builder()
                .title("foo")
                .content("bar")
                .build();

        postRepository.save(post);

        // 클라이언트 요구사항
        // json 응답에서 title 값 길이를 최대 10글자로 해달라


        // when
        PostResponse response = postService.get(post.getId());

        // then
        Assertions.assertNotNull(post);
        assertEquals(1L, postRepository.count());
        assertEquals("foo", response.getTitle());
        assertEquals("bar", response.getContent());
    }

    @Test
    @DisplayName("글 1페이지 조회")
    void test3() {
        //given
        List<Post> requestPosts = IntStream.range(1, 31)
                .mapToObj(i -> Post.builder()
                        .title("동영 제목 - " + i)
                        .content("네카라쿠배 - " + i)
                        .build())
                .collect(Collectors.toList());
        postRepository.saveAll(requestPosts);


        // 클라이언트 요구사항
        // json 응답에서 title 값 길이를 최대 10글자로 해달라


        // when
        Pageable pageable = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "id"));
        List<PostResponse> posts = postService.getList(pageable);

        // then
        assertEquals(5L, posts.size());
        assertEquals("동영 제목 - 30", posts.get(0).getTitle());
        assertEquals("동영 제목 - 26", posts.get(4).getTitle());
    }


}