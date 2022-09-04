package com.rineaubie.api.response;

import com.rineaubie.api.domain.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.*;


/**
 * 서비스 정책에 맞는 클래스
 */
@Getter
public class PostResponse {

    private final Long id;
    private final String title;
    private final String content;

    private final String parsedCreatedDate;
    private static final DateTimeFormatter printDate = ofPattern(("yyyy-MM-dd HH:mm:ss"));

    // 생성자 오버로딩
    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.parsedCreatedDate = post.getCreatedDate().format(printDate);
    }

    @Builder
    public PostResponse(Long id, String title, String content, LocalDateTime createdDate) {
        this.id = id;
        this.title = lengthCheck(title);
        this.content = content;
        this.parsedCreatedDate = createdDate.format(printDate);
    }

    private String lengthCheck(String title) {
        if (title.length() > 10) return title.substring(0, 10) + "...";
        else return title;
    }
}
