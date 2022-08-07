package com.rineaubie.api.response;

import com.rineaubie.api.domain.Post;
import lombok.Builder;
import lombok.Getter;


/**
 * 서비스 정책에 맞는 클래스
 */
@Getter
public class PostResponse {

    private final Long id;
    private final String title;
    private final String content;

    // 생성자 오버로딩
    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
    }

    @Builder
    public PostResponse(Long id, String title, String content) {
        this.id = id;
        this.title = lengthCheck(title);
        this.content = content;
    }

    private String lengthCheck(String title) {
        if (title.length() > 10) return title.substring(0, 10) + "...";
        else return title;
    }
}
