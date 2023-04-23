package com.openclassrooms.mddapi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class SubscriptionDto {
    private Long id;
    @NotNull
    private Long userId;
    @NotNull
    private Long subjectId;
}
