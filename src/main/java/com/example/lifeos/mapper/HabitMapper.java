package com.example.lifeos.mapper;

import com.example.lifeos.dto.habitDtos.HabitCreateDto;
import com.example.lifeos.dto.habitDtos.HabitDetailDto;
import com.example.lifeos.dto.habitDtos.HabitSummaryDto;
import com.example.lifeos.dto.habitDtos.HabitUpdateDto;
import com.example.lifeos.entity.Habit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        uses = HabitLogMapper.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface HabitMapper {

    HabitSummaryDto toSummaryDto(Habit habit);

    Habit toEntity(HabitCreateDto createDto);

    @Mapping(source = "logs", target = "habitLogs")
    HabitDetailDto toDetailDto(Habit habit);

    void updateEntityFromDto(HabitUpdateDto dto, @MappingTarget Habit habit);
}
