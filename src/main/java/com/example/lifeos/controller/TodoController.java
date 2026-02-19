package com.example.lifeos.controller;
import com.example.lifeos.dto.TodoCreateDto;
import com.example.lifeos.dto.TodoResponseDto;
import com.example.lifeos.dto.TodoUpdateDto;
import com.example.lifeos.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @GetMapping
    public ResponseEntity<Page<TodoResponseDto>> readAll(Pageable pageable){
        Page<TodoResponseDto> todos = todoService.readAll(pageable);
        return ResponseEntity.ok(todos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDto> read(@PathVariable UUID id){
        return ResponseEntity.ok(todoService.read(id));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<TodoResponseDto> create(@PathVariable UUID userId, @RequestBody TodoCreateDto dto){
        return ResponseEntity.ok(todoService.create(userId, dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TodoResponseDto> update(@PathVariable UUID id, @RequestBody TodoUpdateDto dto){
        return ResponseEntity.ok(todoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){
        todoService.delete(id);
    }


}