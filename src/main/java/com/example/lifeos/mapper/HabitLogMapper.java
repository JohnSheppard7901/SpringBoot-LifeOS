package com.example.lifeos.mapper;

import com.example.lifeos.dto.habitDtos.HabitLogDto;
import com.example.lifeos.entity.HabitLog;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HabitLogMapper {
    HabitLogDto toDto(HabitLog habitLog);
}
