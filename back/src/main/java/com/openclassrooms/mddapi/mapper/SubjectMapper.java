package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.SubjectDto;
import com.openclassrooms.mddapi.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface SubjectMapper {
    SubjectDto toDto(Subject subject);
    @Mapping(target = "users", ignore = true)
    Subject toEntity(SubjectDto subjectDto);
}
