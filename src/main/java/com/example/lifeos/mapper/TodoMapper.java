package com.example.lifeos.mapper;

import com.example.lifeos.dto.TodoCreateDto;
import com.example.lifeos.dto.TodoResponseDto;
import com.example.lifeos.dto.TodoUpdateDto;
import com.example.lifeos.entity.Todo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TodoMapper {
    TodoResponseDto toResponse(Todo todo);
    List<TodoResponseDto> toResponseList(List<Todo> todos);

    Todo toEntity(TodoResponseDto dto);

    void updateEntityFromDto(TodoUpdateDto dto, @MappingTarget Todo todo);
}
