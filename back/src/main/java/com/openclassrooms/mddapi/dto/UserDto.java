package com.openclassrooms.mddapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
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
public class UserDto {
    private Long id;
    @Size(max = 50)
    @Email
    @NotNull
    private String email;
    @Size(max = 20)
    @NotNull
    private String firstName;
    private boolean admin;
    @JsonIgnore
    @Size(max = 120)
    private String password;
    @NotNull
    private Set<SubjectDto> subjects = new HashSet<>();
    @NotNull
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
