package com.openclassrooms.mddapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class PostDto {
    private Long id;
    @Size(max = 200)
    @NotBlank
    private String title;
    @Size(max = 5000)
    @NotBlank
    private String description;
    @NotNull
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @NotNull
    private Long authorId;
    @NotNull
    private String authorFirstName;
    @NotNull
    private Long subjectId;
    List<MessageDto> messages;
}
