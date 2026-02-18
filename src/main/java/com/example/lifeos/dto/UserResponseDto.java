package com.example.lifeos.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record UserResponseDto(
        UUID id,
        String email,
        String name
) {}
