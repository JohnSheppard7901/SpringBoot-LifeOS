package com.example.lifeos.dto.todoDtos;

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
        LocalDateTime updatedAt,
        UUID userId
) { }
