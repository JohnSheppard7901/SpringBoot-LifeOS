package com.example.lifeos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;


public record TodoCreateDto (
        @NotBlank(message = "Title can't be empty.")
        @Size(min = 3, max = 40)
        String title,
        String description,
        LocalDate deadline
){
}
