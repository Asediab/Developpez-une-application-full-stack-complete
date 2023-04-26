package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.SubjectDto;
import com.openclassrooms.mddapi.dto.UserUpdateDto;
import com.openclassrooms.mddapi.model.Subject;
import com.openclassrooms.mddapi.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserUpdateMapper {

    @Mapping(target = "password", ignore = true)
    UserUpdateDto toDto(User user);

    @Mapping(target = "subjects", source = "userUpdateDto.subjects")
    User toEntity(UserUpdateDto userUpdateDto);

    SubjectDto toDto(Subject subject);

    @Mapping(target = "users", ignore = true)
    Subject toEntity(SubjectDto subjectDto);
}
