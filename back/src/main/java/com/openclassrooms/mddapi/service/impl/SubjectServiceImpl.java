package com.openclassrooms.mddapi.service.impl;

import com.openclassrooms.mddapi.model.Subject;
import com.openclassrooms.mddapi.repository.SubjectRepository;
import com.openclassrooms.mddapi.service.SubjectService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<Subject> getAll() {
        return this.subjectRepository.findAll(Sort.by(Sort.DEFAULT_DIRECTION,"title"));
    }

    @Override
    public Subject getById(Long subjectId) {
        return this.subjectRepository.findById(subjectId).orElse(null);
    }
}
