package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.Subject;
import com.openclassrooms.mddapi.repository.SubjectRepository;
import com.openclassrooms.mddapi.service.impl.SubjectServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class SubjectServiceTest {
    @Autowired
    private SubjectServiceImpl service;
    @MockBean
    private SubjectRepository subjectRepository;

    private Subject subject;
    private ArrayList<Subject> subjectArrayList;

    @BeforeEach
    void start() {
        LocalDateTime localDateTime = LocalDateTime.now();
        subject = new Subject(1L, "Title", "Description", new HashSet<>());
        subjectArrayList = new ArrayList<>();
        subjectArrayList.add(subject);
    }

    @Test
    @DisplayName("Test getAll")
    void testGetAll() {
        doReturn(subjectArrayList).when(subjectRepository).findAll();

        List<Subject> returned = service.getAll();

        Assertions.assertNotNull(returned, "The saved session should not be null");
    }

    @Test
    @DisplayName("Test getById")
    void testGetById() {
        doReturn(Optional.of(subject)).when(subjectRepository).findById(anyLong());

        Subject returned = service.getById(1L);

        Assertions.assertNotNull(returned, "The saved session should not be null");
    }
}
