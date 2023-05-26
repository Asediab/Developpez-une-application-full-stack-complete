package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.model.Subject;
import com.openclassrooms.mddapi.service.impl.SubjectServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashSet;

import static org.mockito.Mockito.doReturn;

@SpringBootTest
class SubjectControllerTest {
    @MockBean
    private SubjectServiceImpl service;

    @Autowired
    private SubjectController subjectController;

    private Subject subject;
    private ArrayList<Subject> subjectArrayList;

    @BeforeEach
    void init() {
        subject = new Subject(1L, "Title", "Description", new HashSet<>());
        subjectArrayList = new ArrayList<>();
        subjectArrayList.add(subject);
    }

    @Test
    @DisplayName("Test FindAll")
    void testFindAll() {
        doReturn(subjectArrayList).when(service).getAll();

        ResponseEntity<?> response = subjectController.findAll();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Test FindAll Not Found")
    void testFindAllNotFound() {
        doReturn(new ArrayList<>()).when(service).getAll();

        ResponseEntity<?> response = subjectController.findAll();

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
