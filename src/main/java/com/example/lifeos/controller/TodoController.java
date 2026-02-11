package com.example.lifeos.controller;
import com.example.lifeos.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @GetMapping
    public void readAll(){

    }

    @GetMapping("/{id}")
    public void read(@PathVariable UUID id){

    }

    @PostMapping
    public void create(){

    }

    @PutMapping
    public void update(){

    }

    @DeleteMapping
    public void delete(){

    }


}