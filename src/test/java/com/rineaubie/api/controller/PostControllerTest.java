package com.rineaubie.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rineaubie.api.domain.Post;
import com.rineaubie.api.repository.PostRepository;
import com.rineaubie.api.request.PostCreate;
import com.rineaubie.api.request.PostEdit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void init() {
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("/posts 요청시 Hello를 출력한다.")
    void test() throws Exception {
        // 글 제목
        // 글 내용
        // 사용자
                // id
                // name
                // level

        /**
         * {
         *      "title": "xxx",
         *      "content": "xxx",
         *      "user": {
         *               "id": "xxx",
         *               "name": "xxx",
         *               "level": "xxx"
         *              }
         * }
         */

        //given
        PostCreate request = PostCreate.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        //  빌더의 장점
        //  - 가독성
        //  - 값 생성에 대한 유연함
        //  - 필요한 값만 받을 수 있다.
        //  - 객체의 불변성

        String json = objectMapper.writeValueAsString(request);

        System.out.println("json = " + json);

        // expected
        mockMvc.perform(post("/posts")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andExpect(content().string(""))
                .andDo(print());
    }
    @Test
    @DisplayName("/posts 요청시 title 값은 필수다.")
    void test2() throws Exception {

        //given
        PostCreate request = PostCreate.builder()
                .content("내용입니다.")
                .build();

        String json = objectMapper.writeValueAsString(request);
        // expected
        mockMvc.perform(post("/posts")
                        .contentType(APPLICATION_JSON)
                // {"title": ""} -> OK
                // {"title": null} -> OK
                        .content(json)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                .andExpect(jsonPath("$.validation.title").value("타이틀을 입력해주세요."))
                .andDo(print());
    }
    @Test
    @DisplayName("/posts 요청시 DB에 값이 저장된다.")
    void test3() throws Exception {

        //given
        PostCreate request = PostCreate.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        String json = objectMapper.writeValueAsString(request);

        // when
        mockMvc.perform(post("/posts")
                        .contentType(APPLICATION_JSON)
                // {"title": ""} -> OK
                // {"title": null} -> OK
                        .content(json)
                )
                .andExpect(status().isOk())
                .andDo(print());

        // then
        Assertions.assertEquals(1L, postRepository.count());

        Post post = postRepository.findAll().get(0);
        assertEquals("제목입니다.", post.getTitle());
        assertEquals("내용입니다.", post.getContent());
    }

//    @Test
//    @DisplayName("equals 테스트 - 재정의 하지 않음")
//    void testEquals() throws Exception {
//        // given
//        Post post = new Post("제목입니다.", "내용입니다.");
//        String json = objectMapper.writeValueAsString(post);
//
//        //when
//        mockMvc.perform(post("/posts")
//                .contentType(APPLICATION_JSON)
//                .content(json)
//        )
//                .andExpect(status().isOk())
//                .andDo(print());
//
//        //then
//        Post findPost = postRepository.findAll().get(0);
//        assertThat(post).isEqualTo(findPost);
//    }

    @Test
    @DisplayName("글 1개 조회")
    void test4() throws Exception {
        // given
        Post post = Post.builder()
                .title("123456789012345")
                .content("bar")
                .build();
        postRepository.save(post);

        // expected
        mockMvc.perform(get("/posts/{postId}", post.getId())
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(post.getId()))
                .andExpect(jsonPath("$.title").value("1234567890"))
                .andExpect(jsonPath("$.content").value("bar"))
                .andDo(print());
    }

    @Test
    @DisplayName("페이지를 0으로 요청하면 첫 페이지를 가져와야 함")
    void test5() throws Exception {
        // given
        List<Post> requestPosts = IntStream.range(1, 31)
                .mapToObj(i -> Post.builder()
                        .title("동영 제목 - " + i)
                        .content("네카라쿠배 - " + i)
                        .build())
                .collect(Collectors.toList());
        postRepository.saveAll(requestPosts);

        // expected
        mockMvc.perform(get("/posts?page=0&size=10")
                .contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.length()",is(10)))
                .andExpect(jsonPath("$[0].id").value(30))
                .andExpect(jsonPath("$[0].title").value("동영 제목 - 30"))
                .andExpect(jsonPath("$[0].content").value("네카라쿠배 - 30"))
                .andExpect(status().isOk())
                /**
                 * [{id: ..., title: ...}, {id: ..., title: ...}]
                 */
                .andDo(print());
    }
    @Test
    @DisplayName("글 제목 수정")
    void test6() throws Exception {
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

        // expected
        mockMvc.perform(patch("/posts/{postId}", post.getId()) // PATCH / /posts/{postId}
                .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(postEdit)))
                .andExpect(status().isOk())
                /**
                 * [{id: ..., title: ...}, {id: ..., title: ...}]
                 */
                .andDo(print());
    }
}