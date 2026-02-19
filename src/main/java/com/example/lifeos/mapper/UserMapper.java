package com.example.lifeos.mapper;

import com.example.lifeos.dto.UserCreateDto;
import com.example.lifeos.dto.UserResponseDto;
import com.example.lifeos.dto.UserUpdateDto;
import com.example.lifeos.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    UserResponseDto toResponseDto(User user);
    User toEntity(UserCreateDto dto);

    void updateEntityFromDto(UserUpdateDto dto, @MappingTarget User user);
}
