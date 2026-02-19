package com.example.lifeos.controller;

import com.example.lifeos.dto.TodoResponseDto;
import com.example.lifeos.dto.TodoUpdateDto;
import com.example.lifeos.service.TodoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.lifeos.dto.TodoCreateDto;
import com.example.lifeos.dto.TodoResponseDto;
import com.example.lifeos.dto.TodoUpdateDto;
import com.example.lifeos.service.TodoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("local")
class TodoControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TodoService todoService;

    @Test
    void shouldCreateTodo() throws Exception {
        UUID todoId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        LocalDate deadline = LocalDate.now();
        LocalDateTime now = LocalDateTime.now();

        TodoResponseDto responseDto = new TodoResponseDto(
                todoId,
                "Feed Max",
                "Max is hungry",
                deadline,
                false,
                now,
                now,
                userId
        );

        when(todoService.create(eq(userId), any(TodoCreateDto.class))).thenReturn(responseDto);

        mockMvc.perform(post("/todos/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "title": "Feed Max",
                                    "description": "Max is hungry",
                                    "deadline": "%s"
                                }
                                """.formatted(deadline)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Feed Max"))
                .andExpect(jsonPath("$.description").value("Max is hungry"))
                .andExpect(jsonPath("$.userId").value(userId.toString()));
    }

    @Test
    void shouldReadTodo() throws Exception {
        UUID todoId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        LocalDate deadline = LocalDate.now();
        LocalDateTime now = LocalDateTime.now();

        TodoResponseDto responseDto = new TodoResponseDto(
                todoId,
                "Feed Max",
                "Max is hungry",
                deadline,
                false,
                now,
                now,
                userId
        );

        when(todoService.read(todoId)).thenReturn(responseDto);

        mockMvc.perform(get("/todos/{id}", todoId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(todoId.toString()))
                .andExpect(jsonPath("$.title").value("Feed Max"));
    }

    @Test
    void shouldUpdateTodo() throws Exception {
        UUID todoId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        LocalDate deadline = LocalDate.now();
        LocalDateTime now = LocalDateTime.now();

        TodoResponseDto updatedDto = new TodoResponseDto(
                todoId,
                "Feed Max Updated",
                "Max is very hungry",
                deadline,
                true,
                now,
                now,
                userId
        );

        when(todoService.update(eq(todoId), any(TodoUpdateDto.class))).thenReturn(updatedDto);

        mockMvc.perform(patch("/todos/{id}", todoId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "title": "Feed Max Updated",
                                    "description": "Max is very hungry",
                                    "deadline": "%s",
                                    "done": true
                                }
                                """.formatted(deadline)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Feed Max Updated"))
                .andExpect(jsonPath("$.done").value(true));
    }

    @Test
    void shouldDeleteTodo() throws Exception {
        UUID todoId = UUID.randomUUID();

        mockMvc.perform(delete("/todos/{id}", todoId))
                .andExpect(status().isOk());
    }


}
