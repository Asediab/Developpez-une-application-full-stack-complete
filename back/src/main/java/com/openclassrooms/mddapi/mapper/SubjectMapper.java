package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.SubjectDto;
import com.openclassrooms.mddapi.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@Mapper(componentModel = "spring")
public interface SubjectMapper {
    SubjectDto toDto(Subject subject);
    @Mapping(target = "users", ignore = true)
    Subject toEntity(SubjectDto subjectDto);

    Set<SubjectDto> mapToDto(Set<Subject> subjects);
    Set<Subject> mapToEntity(Set<SubjectDto> subjectsDto);

    List<SubjectDto> mapToDto(List<Subject> subjects);
    List<Subject> mapToEntity(List<SubjectDto> subjectsDto);
}
