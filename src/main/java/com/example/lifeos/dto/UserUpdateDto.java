package com.example.lifeos.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserUpdateDto(
        @Email
        String email,

        @Size(max = 30)
        String name,

        String password
) {
}
