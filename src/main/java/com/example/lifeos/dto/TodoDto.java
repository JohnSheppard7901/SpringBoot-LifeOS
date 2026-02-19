package com.example.lifeos.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record TodoDto(
        UUID id,
        String title,
        String description,
        LocalDate deadline,
        boolean done
) {
}
