package com.example.lifeos.dto.todoDtos;

import java.time.LocalDate;

public record TodoUpdateDto(
        String title,
        String description,
        LocalDate deadline,
        boolean done
) {
}
