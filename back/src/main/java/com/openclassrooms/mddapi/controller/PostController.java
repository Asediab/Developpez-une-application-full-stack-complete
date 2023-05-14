package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.mapper.PostMapper;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.service.PostService;
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
public class PostController {
    private final PostService service;
    private final PostMapper mapper;

    public PostController(PostService service, PostMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<?> findAllPostsByUser() {
        List<Post> posts = this.service.getAllPostByUserSubscription();
        if (!posts.isEmpty()) {
            return ResponseEntity.ok().body(this.mapper.mapToDto(posts));
        }
        return ResponseEntity.notFound().build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") String id) {
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

    @PostMapping("/new")
    public ResponseEntity<?> create(@Valid @RequestBody PostDto postDto) {
        log.info(postDto);
        Post postDto1 = this.service.create(this.mapper.toEntity(postDto));
        log.info(this.mapper.toEntity(postDto));
        return ResponseEntity.ok().body(this.mapper.toDto(postDto1));
    }
}