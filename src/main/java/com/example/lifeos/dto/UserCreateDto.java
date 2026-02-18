package com.example.lifeos.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record UserCreateDto(
    @Email
    @NotBlank
    String email,

    @NotBlank
    @Size(max = 30)
    String name,

    @NotBlank
    String password
) {}
