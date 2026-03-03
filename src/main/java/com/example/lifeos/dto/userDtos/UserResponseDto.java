package com.example.lifeos.dto.userDtos;

import com.example.lifeos.dto.todoDtos.TodoDto;

import java.util.List;
import java.util.UUID;

public record UserResponseDto(
        UUID id,
        String email,
        String name,
        List<TodoDto> todoList,
        String profilePicId
) {}
