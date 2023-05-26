package com.openclassrooms.mddapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.Subject;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.service.impl.PostServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureJsonTesters
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PostControllerIT {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PostServiceImpl postService;

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
    @WithMockUser(username = "yoga@studio.com")
    void testFindAllPostsByUser() throws Exception {

        MockHttpServletResponse result = mvc
                .perform(get("/api/post"))
                .andExpect(status().isOk())
                .andReturn().getResponse();
    }

    @Test
    @DisplayName("Test FindByID")
    @WithMockUser(username = "yoga@studio.com")
    void testFindById() throws Exception {

        MockHttpServletResponse result = mvc
                .perform(get("/api/post/1"))
                .andExpect(status().isOk())
                .andReturn().getResponse();
    }
}
