package com.example.lifeos.service;

import com.example.lifeos.dto.TodoCreateDto;
import com.example.lifeos.dto.TodoResponseDto;
import com.example.lifeos.entity.Todo;
import com.example.lifeos.entity.User;
import com.example.lifeos.mapper.TodoMapper;
import com.example.lifeos.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {

    //damit mockito mit junit verwendet werden kann, mock faked dependencies
    //so das wir service verwenden können ohne das fehler geworfen. so können wir
    //service isoliert testen ohne die anderen klassen(dependencies) zu testen

    //-- basic setup start
    @Mock
    TodoRepository repository;

    @Mock
    TodoMapper mapper;

    @InjectMocks
    TodoService todoService;
    //-- basic setup end

    UUID id;
    User user;
    LocalDate date;
    LocalDateTime dateTime;
    List<Todo> emptyTodoList;

    TodoResponseDto dto;
    Todo todo;

    @BeforeEach()
    void init(){
        id = UUID.randomUUID();
        date = LocalDate.now();
        dateTime = LocalDateTime.now();

        emptyTodoList = new ArrayList<>();
        user = new User(id, "mail@gmail.com", "John", emptyTodoList);

        dto = new TodoResponseDto(
                id,
                "Feed Max",
                "Max is hungry",
                date,
                false,
                dateTime,
                dateTime
        );
        todo = new Todo(
                id,
                "Feed Max",
                "Max is hungry",
                date,
                false,
                dateTime,
                dateTime,
                user
        );
    }

    @Test
    void readShouldReturnTodo(){
        //Arrange (Vorbedingungen für test setzen)
        when(repository.findById(id)).thenReturn(Optional.of(todo));
        when(mapper.toResponse(todo)).thenReturn(dto);

        //Act
        TodoResponseDto result = todoService.read(id);

        //Assert
        assertEquals(id, result.id());
    }

//    @Test
//    void createShouldSaveNewTodo(){
//        LocalDate deadline = LocalDate.now();
//        TodoCreateDto createDto = new TodoCreateDto(
//                "New Todo",
//                "test if create works",
//                deadline
//        );
//        Todo todoSave = new Todo();
//        todoSave.setId(id);
//        when(repository.save(todoSave)).thenReturn(todo);
//        when(mapper.toResponse(todo)).thenReturn(dto);
//
//        TodoResponseDto result = todoService.create(createDto);
//        assertEquals("New Todo", result.title());
//    }

    @Test
    void createShouldSaveNewTodo() {
        LocalDate deadline = LocalDate.now();

        TodoCreateDto createDto = new TodoCreateDto(
                "Feed Max",
                "test if create works",
                deadline
        );

        Todo savedTodo = new Todo();
        savedTodo.setId(id);
        savedTodo.setTitle("Feed Max");
        savedTodo.setDescription("test if create works");
        savedTodo.setDeadline(deadline);

        when(repository.save(any(Todo.class))).thenReturn(savedTodo);
        when(mapper.toResponse(savedTodo)).thenReturn(dto);

        TodoResponseDto result = todoService.create(createDto);

        assertEquals("Feed Max", result.title());
    }

}
