package com.rineaubie.api.repository;

import com.rineaubie.api.domain.Post;
import com.rineaubie.api.request.PostSearch;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> getList(PostSearch postSearch);
}
