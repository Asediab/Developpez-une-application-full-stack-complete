package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.SubjectDto;
import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.model.Subject;
import com.openclassrooms.mddapi.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;


@Mapper (componentModel = MappingConstants.ComponentModel.CDI)
public interface UserMapper{
    UserDto toDto(User user);
    @Mapping(target = "subjects", source = "userDto.subjects")
    User toEntity(UserDto userDto);

    SubjectDto toDto(Subject subject);
    @Mapping(target = "users", expression = "java(java.util.Collections.emptySet())")
    Subject toEntity(SubjectDto subjectDto);
}
