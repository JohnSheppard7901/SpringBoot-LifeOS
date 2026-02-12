package com.example.lifeos.mapper;

import com.example.lifeos.dto.TodoCreateDto;
import com.example.lifeos.dto.TodoResponseDto;
import com.example.lifeos.entity.Todo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoMapper {
    TodoResponseDto toResponse(Todo todo);
    List<TodoResponseDto> toResponseList(List<Todo> todos);

    Todo toEntity(TodoResponseDto dto);

}
