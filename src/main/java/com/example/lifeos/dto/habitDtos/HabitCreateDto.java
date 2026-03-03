package com.example.lifeos.dto.habitDtos;

import com.example.lifeos.enums.Frequency;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record HabitCreateDto(
        @NotBlank
        String name,
        String description,
        @NotNull
        Frequency frequency
) {}
