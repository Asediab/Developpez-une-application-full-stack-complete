package com.openclassrooms.mddapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class UserUpdateDto {

    private Long id;
    @Size(max = 50)
    @Email
    @NotNull
    private String email;
    @Size(max = 20)
    @NotNull
    private String firstName;
    private boolean admin;
    @NotBlank
    @Size(max = 120)
    private String password;
    private Set<SubjectDto> subjects = new HashSet<>();
    @NotNull
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
