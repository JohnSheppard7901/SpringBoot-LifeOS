package com.example.lifeos.controller;

import com.example.lifeos.dto.TodoResponseDto;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("local")
class TodoControllerIT {
    //IT steht für integration test
    // @SpringBootTest sagt das bei dem test spring boot gestartet werden soll
    // Mock Mvc ist für ausführen von Crud methoden

    //service und controller währden getestet, ob sie zusammen funktionieren, repo wiedr weiter gemocked, damti ich db verbindung nicht brauche
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @MockitoBean
//    TodoService service;
//
//    @Test
//    void shouldCreateTodo() throws Exception {
//        UUID id = UUID.randomUUID();
//        LocalDate date = LocalDate.now();
//        LocalDateTime dateTime = LocalDateTime.now();
//
//        TodoResponseDto responseDto = new TodoResponseDto(
//                id,
//                "Feed Max",
//                "Max is hungry",
//                date,
//                false,
//                dateTime,
//                dateTime
//        );
//        when(service.create(any())).thenReturn(responseDto);
//
//        mockMvc.perform(
//                        post("/todos")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content("""
//                                            {
//                                              "title": "Feed Max"
//                                            }
//                                        """))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.title").value("Feed Max"));
//
//
//    }


}
