package com.openclassrooms.mddapi.controller;


import com.openclassrooms.mddapi.mapper.SubjectMapper;
import com.openclassrooms.mddapi.model.Subject;
import com.openclassrooms.mddapi.service.SubjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/subject")
@Log4j2
@Tag(name = "Subject", description = "The Subject API. Contains all the operations " +
        "that can be performed a Subject.")
public class SubjectController {
    private final SubjectService service;
    private final SubjectMapper mapper;

    public SubjectController(SubjectService service, SubjectMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    /**
     * Get all subjects
     *
     * @return The HTTP response with a List of Subjects
     */
    @Operation(summary = "Get all subjects from DB")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Subject> subjects = this.service.getAll();
        if (!subjects.isEmpty()) {
            return ResponseEntity.ok().body(this.mapper.mapToDto(subjects));
        }
        return ResponseEntity.notFound().build();
    }
}
