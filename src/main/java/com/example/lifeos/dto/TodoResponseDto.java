package com.example.lifeos.dto;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record TodoResponseDto(
        UUID id,
        String title,
        String description,
        LocalDate deadline,
        boolean done,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
