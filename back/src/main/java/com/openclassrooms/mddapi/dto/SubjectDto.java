package com.openclassrooms.mddapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class SubjectDto {
    private Long id;
    @NotBlank
    @Size(max = 255)
    private String title;
    @NotBlank
    @Size(max = 500)
    private String description;
}
