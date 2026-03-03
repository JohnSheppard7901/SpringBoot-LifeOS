package com.example.lifeos.dto.habitDtos;

import com.example.lifeos.enums.Frequency;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record HabitDetailDto(
        UUID id,
        String name,
        String description,
        Frequency frequency,
        LocalDateTime createdAt,
        List<HabitLogDto> habitLogs
) {
}
