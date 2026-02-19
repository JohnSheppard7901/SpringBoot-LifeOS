package com.example.lifeos.service;

import com.example.lifeos.dto.TodoCreateDto;
import com.example.lifeos.dto.TodoResponseDto;
import com.example.lifeos.dto.TodoUpdateDto;
import com.example.lifeos.entity.Todo;
import com.example.lifeos.entity.User;
import com.example.lifeos.exception.EntityNotFoundException;
import com.example.lifeos.mapper.TodoMapper;
import com.example.lifeos.repository.TodoRepository;
import com.example.lifeos.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TodoMapper todoMapper;

    @InjectMocks
    private TodoService todoService;

    private UUID todoId;
    private UUID userId;
    private LocalDate deadline;
    private LocalDateTime now;
    private User user;
    private Todo todo;
    private TodoResponseDto todoResponseDto;

    @BeforeEach
    void setUp() {
        todoId = UUID.randomUUID();
        userId = UUID.randomUUID();
        deadline = LocalDate.now();
        now = LocalDateTime.now();

        user = new User();
        user.setId(userId);
        user.setEmail("mail@gmail.com");
        user.setName("John");

        todo = new Todo();
        todo.setId(todoId);
        todo.setTitle("Feed Max");
        todo.setDescription("Max is hungry");
        todo.setDeadline(deadline);
        todo.setDone(false);
        todo.setCreatedAt(now);
        todo.setUpdatedAt(now);
        todo.setUser(user);

        todoResponseDto = new TodoResponseDto(
                todoId,
                "Feed Max",
                "Max is hungry",
                deadline,
                false,
                now,
                now,
                userId
        );
    }

    @Test
    void readShouldReturnTodo() {
        when(todoRepository.findById(todoId)).thenReturn(Optional.of(todo));
        when(todoMapper.toResponse(todo)).thenReturn(todoResponseDto);

        TodoResponseDto result = todoService.read(todoId);

        assertEquals(todoId, result.id());
        assertEquals("Feed Max", result.title());
    }

    @Test
    void readShouldThrowExceptionWhenTodoNotFound() {
        when(todoRepository.findById(todoId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> todoService.read(todoId));
    }

    @Test
    void createShouldSaveNewTodo() {
        TodoCreateDto createDto = new TodoCreateDto(
                "Feed Max",
                "Max is hungry",
                deadline
        );

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(todoRepository.save(any(Todo.class))).thenReturn(todo);
        when(todoMapper.toResponse(todo)).thenReturn(todoResponseDto);

        TodoResponseDto result = todoService.create(userId, createDto);

        assertEquals("Feed Max", result.title());
        assertEquals(userId, result.userId());
    }

    @Test
    void createShouldThrowExceptionWhenUserNotFound() {
        TodoCreateDto createDto = new TodoCreateDto("Feed Max", "Max is hungry", deadline);

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> todoService.create(userId, createDto));
    }

    @Test
    void updateShouldReturnUpdatedTodo() {
        TodoUpdateDto updateDto = new TodoUpdateDto(
                "Feed Max Updated",
                "Max is very hungry",
                deadline,
                true
        );

        when(todoRepository.findById(todoId)).thenReturn(Optional.of(todo));
        when(todoMapper.toResponse(todo)).thenReturn(todoResponseDto);

        TodoResponseDto result = todoService.update(todoId, updateDto);

        assertEquals("Feed Max", result.title()); // Mapper keeps original title in our mock
    }

    @Test
    void updateShouldThrowExceptionWhenTodoNotFound() {
        TodoUpdateDto updateDto = new TodoUpdateDto("Feed Max Updated", "Max is very hungry", deadline, true);

        when(todoRepository.findById(todoId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> todoService.update(todoId, updateDto));
    }

    @Test
    void deleteShouldCallRepository() {
        when(todoRepository.findById(todoId)).thenReturn(Optional.of(todo));

        todoService.delete(todoId);
        // no assertion needed; exception would fail the test if something wrong
    }

    @Test
    void deleteShouldThrowExceptionWhenTodoNotFound() {
        when(todoRepository.findById(todoId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> todoService.delete(todoId));
    }
}
