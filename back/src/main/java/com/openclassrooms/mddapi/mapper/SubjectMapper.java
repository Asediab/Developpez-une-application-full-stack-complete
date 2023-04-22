package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.SubjectDto;
import com.openclassrooms.mddapi.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface SubjectMapper {
    SubjectDto toDto(Subject subject);
    @Mapping(target = "users", expression = "java(java.util.Collections.emptySet())")
    Subject toEntity(SubjectDto subjectDto);
}
