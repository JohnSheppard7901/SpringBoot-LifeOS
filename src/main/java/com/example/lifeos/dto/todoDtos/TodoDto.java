package com.example.lifeos.dto.todoDtos;

import java.time.LocalDate;
import java.util.UUID;

public record TodoDto(
        UUID id,
        String title,
        String description,
        LocalDate deadline,
        boolean done
) {
}
