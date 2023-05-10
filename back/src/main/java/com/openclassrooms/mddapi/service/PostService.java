package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.Subject;

import java.util.List;
import java.util.Set;


public interface PostService {
    List<Post> getAllPostByUserSubscription(Set<Subject> subjectSet);

    Post create(Post post);

    Post getById(Long postId);
}
