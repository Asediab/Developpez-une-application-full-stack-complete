package com.openclassrooms.mddapi.service.impl;

import com.openclassrooms.mddapi.exeption.NotFoundException;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.PostRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.service.PostService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Post> getAllPostByUserSubscription() {
        User currentUser = new User();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            currentUser = userRepository.findByEmail(currentUserName).orElseThrow(() -> new NotFoundException("User with this Mail not found"));
        }
        return this.postRepository.findBySubjectInOrderByCreatedAt(currentUser.getSubjects());
    }

    @Override
    public Post create(Post post) {
        post.setCreatedAt(LocalDateTime.now());
        return this.postRepository.save(post);
    }

    @Override
    public Post getById(Long postId) {
        return this.postRepository.findById(postId).orElse(null);
    }
}
