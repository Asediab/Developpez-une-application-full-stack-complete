package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.Subject;

import java.util.List;

public interface SubjectService {
    List<Subject> getAll();
    Subject getById(Long subjectId);
}
