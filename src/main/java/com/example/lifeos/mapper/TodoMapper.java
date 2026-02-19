package com.example.lifeos.mapper;

import com.example.lifeos.dto.TodoDto;
import com.example.lifeos.dto.TodoResponseDto;
import com.example.lifeos.dto.TodoUpdateDto;
import com.example.lifeos.entity.Todo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TodoMapper {
    @Mapping(target = "userId", source = "user.id")
    TodoResponseDto toResponse(Todo todo);
    List<TodoResponseDto> toResponseList(List<Todo> todos);

    TodoDto toDto(Todo todo);
    List<TodoDto> toDtoList(List<Todo> todos);

    Todo toEntity(TodoResponseDto dto);

    void updateEntityFromDto(TodoUpdateDto dto, @MappingTarget Todo todo);
}
