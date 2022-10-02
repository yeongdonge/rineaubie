package com.rineaubie.api.repository;

import com.rineaubie.api.domain.Post;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostRepositoryImplTest {

    @Autowired
    PostRepository postRepository;

    @LocalServerPort
    private int port;



    /**
     * Auditing 추가 테스트
     */
    @Test
    @DisplayName("CreatedDateTime이 정상 입력되어야 함")
    void AUDITING_테스트() throws Exception
    {
        //given
        Post post = Post.builder()
                .title("제목")
                .content("내용")
                .build();

        postRepository.save(post);
        //when
        List<Post> posts = postRepository.findAll();
        Post findPost = posts.get(0);

        //then
        log.info("CreatedDate={}", findPost.getCreatedDate());
        Assertions.assertThat(findPost.getCreatedDate()).isAfter(LocalDateTime.of(2021, 1, 1, 1, 1));
    }


}