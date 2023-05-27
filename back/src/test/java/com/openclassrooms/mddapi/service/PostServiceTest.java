package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.Subject;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.PostRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.security.service.UserDetailsImpl;
import com.openclassrooms.mddapi.service.impl.PostServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class PostServiceTest {
    @Autowired
    private PostServiceImpl postService;

    @MockBean
    private PostRepository postRepository;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private Authentication authentication;

    private Post post;
    private User user;
    private UserDetailsImpl userDetails;
    private ArrayList<Post> postArrayList;

    @BeforeEach
    void start() {
        LocalDateTime localDateTime = LocalDateTime.now();
        post = new Post(1L, "Title", "Desccription", localDateTime, localDateTime, user, new Subject(), new ArrayList<>());
        user = new User(1L, "email", "firstName", "password", true, localDateTime
                , localDateTime, new HashSet<>() {
        });
        userDetails = new UserDetailsImpl(1L, "email", "firstName", "password", localDateTime, localDateTime);
        postArrayList = new ArrayList<>();
        postArrayList.add(post);
    }

    @Test
    @DisplayName("Test getAllPostByUserSubscription")
    void testGetAllPostByUserSubscription() {
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(userDetails);
        doReturn(user.getEmail()).when(authentication).getName();
        doReturn(Optional.of(user)).when(userRepository).findByEmail(anyString());
        doReturn(postArrayList).when(postRepository).findBySubjectInOrderByCreatedAt(anySet());

        List<Post> returned = postService.getAllPostByUserSubscription();

        Assertions.assertNotNull(returned, "The saved session should not be null");
        Assertions.assertEquals(postArrayList, returned, "Sessions should de equals");
    }

    @Test
    @DisplayName("Test Create")
    void testCreate() {
        doReturn(post).when(postRepository).save(any());

        Post returned = postService.create(post);

        Assertions.assertNotNull(returned, "The saved session should not be null");
    }

    @Test
    @DisplayName("Test getById")
    void testGetById() {
        doReturn(Optional.of(post)).when(postRepository).findById(any());

        Post returned = postService.getById(1L);

        Assertions.assertNotNull(returned, "The saved session should not be null");
    }
}
