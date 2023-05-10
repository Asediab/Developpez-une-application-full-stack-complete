package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.MessageDto;
import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.model.Message;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.service.impl.SubjectServiceImpl;
import com.openclassrooms.mddapi.service.impl.UserServiceImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public abstract class PostMapper {
    @Autowired
    protected UserServiceImpl userService;
    @Autowired
    protected SubjectServiceImpl subjectService;

    @Mapping(target = "authorFirstName", expression =
            "java(post.getAuthor() != null ? post.getAuthor().getFirstName() : null)")
    @Mapping(target = "subjectId", expression =
            "java(post.getSubject() != null ? post.getSubject().getId() : null)")
    @Mapping(target = "authorId", expression =
            "java(post.getAuthor() != null ? post.getAuthor().getId() : null)")
    public abstract PostDto toDto(Post post);

    @Mapping(target = "subject", expression =
            "java(postDto.getSubjectId() != null ? this.subjectService.getById(postDto.getSubjectId()) : null)")
    @Mapping(target = "author", expression =
            "java(postDto.getAuthorId() != null ? this.userService.getById(postDto.getAuthorId()) : null)")
    public abstract Post toEntity(PostDto postDto);

    public abstract List<PostDto> mapToDto(List<Post> posts);

    public abstract List<Post> mapToEntity(List<PostDto> postsDto);

    @Mapping(target = "authorId", source = "message.author.id")
    @Mapping(target = "postId", source = "message.post.id")
    @Mapping(target = "authorFirstName", source = "message.author.firstName")
    public abstract MessageDto toDto(Message message);

    @Mapping(target = "post", ignore = true)
    @Mapping(target = "author", ignore = true)
    public abstract Message toEntity(MessageDto messageDto);
}
