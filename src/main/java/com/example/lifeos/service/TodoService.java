package com.example.lifeos.service;

import com.example.lifeos.dto.TodoCreateDto;
import com.example.lifeos.dto.TodoResponseDto;
import com.example.lifeos.entity.Todo;
import com.example.lifeos.exception.EntityNotFoundException;
import com.example.lifeos.mapper.TodoMapper;
import com.example.lifeos.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;

    public TodoResponseDto read (UUID id){
        Todo todo = todoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Todo doesn't exist.")
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

}
