package com.openclassrooms.mddapi.service.impl;

import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.Subject;
import com.openclassrooms.mddapi.repository.PostRepository;
import com.openclassrooms.mddapi.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getAllPostByUserSubscription(Set<Subject> subjectSet) {
        return this.postRepository.findBySubjectInOrderByCreatedAt(subjectSet);
    }

    @Override
    public Post create(Post post) {
        return this.postRepository.save(post);
    }

    @Override
    public Post getById(Long postId) {
        return this.postRepository.findById(postId).orElse(null);
    }
}
