package com.rineaubie.api.service;

import com.rineaubie.api.domain.Post;
import com.rineaubie.api.repository.PostRepository;
import com.rineaubie.api.request.PostCreate;
import com.rineaubie.api.response.PostResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void write(PostCreate postCreate) {
        // postCreate -> Entity

        Post post = Post.builder()
                .title(postCreate.getTitle())
                .content(postCreate.getContent())
                .build();

        //case1. 저장한 데이터 Entity - response 응답하기
        //case2. 저장한 데이터 pk - response 응답하기
        //          Client에서는 수신한 id를 글 조회 API를 통해서 글 데이터를 수신받음
        //case3. 응답 필요 없음 -> 클라이언트에서 모든 POST(글) 데이터 context를 잘 관리함
        //bad case: 서버에서 -> 반드이 이렇게 할겁니다! fiㅌ
        //                    -> 서버에서는 차라리 유연하게 대응하는 것이 좋음
        //                    -> 한번에 일괄적으로 잘 처리되는 케이스가 없음 -> 잘 관리하는 형태가 중요

        postRepository.save(post);
    }

    public PostResponse get(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다."));

        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }

    // 글이 많은 경우 -> 비용이 많이 든다.
    public List<PostResponse> getList(Pageable pageable) {
        // web -> page 1 -> 0
//        Pageable pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.DESC, "id"));
        return postRepository.findAll(pageable).stream()
                .map(PostResponse::new)
                .collect(Collectors.toList());
    }
}