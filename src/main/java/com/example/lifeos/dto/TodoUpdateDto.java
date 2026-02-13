package com.example.lifeos.dto;

import java.time.LocalDate;

public record TodoUpdateDto(
        String title,
        String description,
        LocalDate deadline,
        boolean done
) {
}
