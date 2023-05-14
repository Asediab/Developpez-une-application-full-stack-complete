package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.Post;

import java.util.List;


public interface PostService {
    List<Post> getAllPostByUserSubscription();

    Post create(Post post);

    Post getById(Long postId);
}
