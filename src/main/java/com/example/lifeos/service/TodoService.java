package com.example.lifeos.service;

import com.example.lifeos.dto.TodoCreateDto;
import com.example.lifeos.dto.TodoResponseDto;
import com.example.lifeos.dto.TodoUpdateDto;
import com.example.lifeos.entity.Todo;
import com.example.lifeos.exception.EntityNotFoundException;
import com.example.lifeos.mapper.TodoMapper;
import com.example.lifeos.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;

    public Page<TodoResponseDto> readAll(Pageable pageable) {
        Page<Todo> todos = todoRepository.findAll(pageable);
        return todos.map(todoMapper::toResponse);
    }

    public TodoResponseDto read (UUID id){
        Todo todo = todoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(id)
        );
        return todoMapper.toResponse(todo);
    }

    public TodoResponseDto create (TodoCreateDto dto){
        Todo todo = new Todo();
        todo.setTitle(dto.title());
        todo.setDescription(dto.description());
        todo.setDeadline(dto.deadline());

        Todo savedTodo = todoRepository.save(todo);

        return todoMapper.toResponse(savedTodo);
    }

    @Transactional
    public TodoResponseDto update(UUID id, TodoUpdateDto dto){

        Todo todo = todoRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException(id)
        );

        if (!dto.title().isBlank() && !dto.title().isEmpty() && dto.title() != null){
            todo.setTitle(dto.title());
        }

        todo.setDescription(dto.description());
        todo.setDeadline(dto.deadline());
        todo.setDone(dto.done());

        Todo updatedTodo = todoRepository.save(todo);
        return todoMapper.toResponse(updatedTodo);
    }

    @Transactional
    public void delete(UUID id){
        todoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(id)
        );

        todoRepository.deleteById(id);
    }

}
