package com.openclassrooms.mddapi.controller;


import com.openclassrooms.mddapi.mapper.SubjectMapper;
import com.openclassrooms.mddapi.model.Subject;
import com.openclassrooms.mddapi.service.SubjectService;
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
public class SubjectController {
    private final SubjectService service;
    private final SubjectMapper mapper;

    public SubjectController(SubjectService service, SubjectMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Subject> subjects = this.service.getAll();
        if (!subjects.isEmpty()) {
            return ResponseEntity.ok().body(this.mapper.mapToDto(subjects));
        }
        return ResponseEntity.notFound().build();
    }
}
