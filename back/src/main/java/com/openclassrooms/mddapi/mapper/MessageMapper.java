package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.MessageDto;
import com.openclassrooms.mddapi.model.Message;
import com.openclassrooms.mddapi.service.PostService;
import com.openclassrooms.mddapi.service.UserService;
import com.openclassrooms.mddapi.service.impl.PostServiceImpl;
import com.openclassrooms.mddapi.service.impl.UserServiceImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public abstract class MessageMapper {
    @Autowired
    protected PostService postService;
    @Autowired
    protected UserServiceImpl userService;
    @Mapping(target = "authorId", source = "author.id")
    @Mapping(target = "postId", source = "post.id")
    @Mapping(target = "authorFirstName", source = "author.firstName")
    public abstract MessageDto toDto(Message message);

    @Mapping(target = "post", expression =
            "java(messageDto.getPostId() != null ? this.postService.getById(messageDto.getPostId()) : null)")
    @Mapping(target = "author", expression =
            "java(messageDto.getAuthorId() != null ? this.userService.getById(messageDto.getAuthorId()) : null)")
   public abstract Message toEntity(MessageDto messageDto);
}
