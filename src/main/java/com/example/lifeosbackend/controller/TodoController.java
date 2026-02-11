package com.example.lifeosbackend.controller;

import com.example.lifeosbackend.service.TodoService;

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

    @DeletMapping
    public void delete(){

    }


}
