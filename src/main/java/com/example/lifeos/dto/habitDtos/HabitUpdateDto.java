package com.example.lifeos.dto.habitDtos;

import com.example.lifeos.enums.Frequency;

public record HabitUpdateDto(
        String name,
        String description,
        Frequency frequency
) {
}
