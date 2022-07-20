package com.rineaubie.api.service;

import com.rineaubie.api.domain.Post;
import com.rineaubie.api.repository.PostRepository;
import com.rineaubie.api.request.PostCreate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.InjectService;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void write(PostCreate postCreate) {
        // postCreate -> Entity

        Post post = new Post(postCreate.getTitle(), postCreate.getContent());


        postRepository.save(post);
    }

}
