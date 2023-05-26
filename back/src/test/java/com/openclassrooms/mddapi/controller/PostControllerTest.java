package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.Subject;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.service.PostService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class PostControllerTest {
    @MockBean
    private PostService service;

    @Autowired
    private PostController postController;


    private Post post;
    private PostDto postDto;
    private User user;
    private List<Post> postList;
    private List<PostDto> postDtoList;
    private final String email = "yoga2023@studio.com";


    @BeforeEach
    void init() {
        LocalDateTime localDateTime = LocalDateTime.now();
        user = new User(1L, email, "firstName", "password", true, LocalDateTime.now(), LocalDateTime.now(), new HashSet<>() {
        });
        post = new Post(1L, "Post", "Description", localDateTime, localDateTime, user, new Subject(), new ArrayList<>());
        postDto = new PostDto(1L, "Post", "Description", localDateTime, localDateTime, user.getId(), user.getFirstName(), null, null, new ArrayList<>());
        postList = new ArrayList<>();
        postDtoList = new ArrayList<>();
        postDtoList.add(postDto);
        postList.add(post);
    }

    @Test
    @DisplayName("Test FindAllPostsByUser")
    void testFindAllPostsByUser() {
        doReturn(postList).when(service).getAllPostByUserSubscription();

        ResponseEntity<?> response = postController.findAllPostsByUser();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(response.getBody(), postDtoList);
    }

    @Test
    @DisplayName("Test FindById")
    void testFindById() {
        doReturn(post).when(service).getById(anyLong());

        ResponseEntity<?> response = postController.findById("1");

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(response.getBody(), postDto);
    }

    @Test
    @DisplayName("Test FindById Not Found")
    void testFindByIdNotFound() {
        doReturn(null).when(service).getById(anyLong());

        ResponseEntity<?> response = postController.findById("1");

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    @DisplayName("Test Create")
    void testCreate() {
        doReturn(post).when(service).create(any());

        ResponseEntity<?> response = postController.create(postDto);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(response.getBody(), postDto);
    }
}
