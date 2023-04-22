package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.MessageDto;
import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.model.Message;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.service.SubjectService;
import com.openclassrooms.mddapi.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public abstract class PostMapper {
    @Autowired
    protected UserService userService;
    @Autowired
    protected SubjectService subjectService;
    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(target = "subjectId", source = "subject.id")
    @Mapping(target = "authorId", source = "author.id")
    public abstract PostDto toDto(Post post);

    // TODO ferify mapping of MessageDto in PostDto
    @Mapping(target = "subject", expression =
            "java(postDto.getSubjectId() != null ? this.subjectService.getById(postDto.getSubjectId()) : null)")
    @Mapping(target = "author", expression =
            "java(postDto.getAuthorId() != null ? this.userService.getById(postDto.getAuthorId()) : null)")
    public abstract Post toEntity(PostDto postDto);

    @Mapping(target = "authorId", source = "author.id")
    @Mapping(target = "postId", source = "post.id")
    @Mapping(target = "authorFirstName", source = "author.firstName")
    public abstract MessageDto toDto(Message message);

    @Mapping(target = "post", source = "")
    @Mapping(target = "author", source = "")
    public abstract Message toEntity(MessageDto messageDto);
}
