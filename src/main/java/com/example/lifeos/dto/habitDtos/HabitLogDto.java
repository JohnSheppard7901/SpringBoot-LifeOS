package com.example.lifeos.dto.habitDtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record HabitLogDto(
        UUID id,
        LocalDateTime completedAt
) {
}
