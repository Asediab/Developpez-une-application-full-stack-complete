package com.openclassrooms.mddapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class MessageDto {
    private Long id;
    @NotNull
    private LocalDateTime createdAt;
    @NotBlank
    @Size(max = 500)
    private String message;
    @NotNull
    private String authorFirstName;
    private Long authorId;
    @NotNull
    private Long postId;
}
