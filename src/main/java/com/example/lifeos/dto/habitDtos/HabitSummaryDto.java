package com.example.lifeos.dto.habitDtos;

import com.example.lifeos.enums.Frequency;

import java.util.UUID;

public record HabitSummaryDto(
        UUID id,
        String name,
        String description,
        Frequency frequency
) {}