package com.example.lifeos.dto;

import java.util.List;
import java.util.UUID;

public record UserResponseDto(
        UUID id,
        String email,
        String name,
        List<TodoDto> todoList
) {}
