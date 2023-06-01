package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.mapper.PostMapper;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/post")
@Log4j2
@Tag(name = "Post", description = "The Post API. Contains all operations " +
        "that can be performed a Post.")
public class PostController {
    private final PostService service;
    private final PostMapper mapper;

    public PostController(PostService service, PostMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    /**
     * Get all posts by User
     *
     * @return The HTTP response with List of Posts
     */
    @Operation(summary = "Get all posts on the subject of which the user is subscribed")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping
    public ResponseEntity<?> findAllPostsByUser() {
        List<Post> posts = this.service.getAllPostByUserSubscription();
        if (!posts.isEmpty()) {
            return ResponseEntity.ok().body(this.mapper.mapToDto(posts));
        }
        return ResponseEntity.notFound().build();

    }

    /**
     * Get the post by Id
     *
     * @param id id of Post to be searched
     * @return The HTTP response with the Post
     */
    @Operation(summary = "Get a post by his id")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@Parameter(description = "id of Post to be searched") @PathVariable("id") String id) {
        try {
            Post post = this.service.getById(Long.valueOf(id));
            if (post == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().body(this.mapper.toDto(post));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Create a new post
     *
     * @param postDto credential for creating a new Post
     * @return The HTTP response with the Post created
     */
    @Operation(summary = "Create a new post")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Object of PostDto.class")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/new")
    public ResponseEntity<?> create(@Valid @RequestBody PostDto postDto) {
        Post postDto1 = this.service.create(this.mapper.toEntity(postDto));
        return ResponseEntity.ok().body(this.mapper.toDto(postDto1));
    }
}
