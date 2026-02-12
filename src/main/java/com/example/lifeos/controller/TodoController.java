package com.example.lifeos.controller;
import com.example.lifeos.dto.TodoCreateDto;
import com.example.lifeos.dto.TodoResponseDto;
import com.example.lifeos.entity.Todo;
import com.example.lifeos.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.UUID;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @GetMapping
    public void readAll(){

    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDto> read(@PathVariable UUID id){
        return ResponseEntity.ok(todoService.read(id));
    }

    @PostMapping
    public ResponseEntity<TodoResponseDto> create(@RequestBody TodoCreateDto dto){
        return ResponseEntity.ok(todoService.create(dto));
    }

    @PutMapping
    public void update(){

    }

    @DeleteMapping
    public void delete(){

    }


}