package com.rineaubie.api.service;

import com.rineaubie.api.domain.Post;
import com.rineaubie.api.exception.PostNotFound;
import com.rineaubie.api.repository.PostRepository;
import com.rineaubie.api.request.PostCreate;
import com.rineaubie.api.request.PostEdit;
import com.rineaubie.api.request.PostSearch;
import com.rineaubie.api.response.PostResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        List<Post> requestPosts = IntStream.range(0, 20)
                .mapToObj(i -> Post.builder()
                        .title("동영 제목 - " + i)
                        .content("네카라쿠배 - " + i)
                        .build())
                .collect(Collectors.toList());
        postRepository.saveAll(requestPosts);


        // 클라이언트 요구사항
        // json 응답에서 title 값 길이를 최대 10글자로 해달라


        // when
//        Pageable pageable = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "id"));
        PostSearch postSearch = PostSearch.builder()
                .page(1)
                .size(10)
                .build();
        List<PostResponse> posts = postService.getList(postSearch);

        // then
        assertEquals(10L, posts.size());
        assertEquals("동영 제목 - 19", posts.get(0).getTitle());
    }

    @Test
    @DisplayName("글 제목 수정")
    void test4() {
        //given
        Post post = Post.builder()
                .title("동영 제목")
                .content("네카라쿠배")
                .build();
        postRepository.save(post);

        // 수정할 내용
        PostEdit postEdit = PostEdit.builder()
                .title("승혜 제목")
                .content("네카라쿠배")
                .build();


        // when
        postService.edit(post.getId(), postEdit);

        // then
        Post changedPost = postRepository.findById(post.getId())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 글. id = " + post.getId()));

        Assertions.assertEquals("승혜 제목", changedPost.getTitle());
        Assertions.assertEquals("네카라쿠배", changedPost.getContent());
    }
    @Test
    @DisplayName("글 내용 수정")
    void test5() {
        //given
        Post post = Post.builder()
                .title("동영 제목")
                .content("네카라쿠배")
                .build();
        postRepository.save(post);

        // 수정할 내용
        PostEdit postEdit = PostEdit.builder()
                .title("동영 제목")
                .content("쿠배당토")
                .build();


        // when
        postService.edit(post.getId(), postEdit);

        // then
        Post changedPost = postRepository.findById(post.getId())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 글. id = " + post.getId()));

        Assertions.assertEquals("동영 제목", changedPost.getTitle());
        Assertions.assertEquals("쿠배당토", changedPost.getContent());
    }

    @Test
    @DisplayName("게시글 삭제")
    void test6() {
        //given
        Post post = Post.builder()
                .title("동영 제목")
                .content("네카라쿠배")
                .build();
        postRepository.save(post);

        //when
        postService.delete(post.getId());

        //then
        Assertions.assertEquals(0,postRepository.count());
    }

    @Test
    @DisplayName("글 1개 조회 - 실패")
    void test7() {
        //given
        Post post = Post.builder()
                .title("동영")
                .content("네카라쿠배")
                .build();

        postRepository.save(post);

        // post.getId() // primary_id = 1


        // expected
        Assertions.assertThrows(PostNotFound.class, () -> {
            postService.get(post.getId() + 1L);
        });
    }

    @Test
    @DisplayName("게시글 삭제 - 실패")
    void test8() {
        //given
        Post post = Post.builder()
                .title("동영 제목")
                .content("네카라쿠배")
                .build();
        postRepository.save(post);

        //expected
        Assertions.assertThrows(PostNotFound.class, () -> {
            postService.delete(post.getId()+1L);
        });
    }
    @Test
    @DisplayName("글 내용 수정 - 실패")
    void test9() {
        //given
        Post post = Post.builder()
                .title("동영 제목")
                .content("네카라쿠배")
                .build();
        postRepository.save(post);

        // 수정할 내용
        PostEdit postEdit = PostEdit.builder()
                .title("동영 제목")
                .content("쿠배당토")
                .build();

        // expected
        Assertions.assertThrows(PostNotFound.class, () -> {
            postService.edit(post.getId() + 1L, postEdit);
        });
    }


}