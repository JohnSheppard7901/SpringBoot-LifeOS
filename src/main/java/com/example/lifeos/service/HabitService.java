package com.example.lifeos.service;

import com.example.lifeos.dto.habitDtos.HabitCreateDto;
import com.example.lifeos.dto.habitDtos.HabitDetailDto;
import com.example.lifeos.dto.habitDtos.HabitSummaryDto;
import com.example.lifeos.dto.habitDtos.HabitUpdateDto;
import com.example.lifeos.entity.Habit;
import com.example.lifeos.entity.User;
import com.example.lifeos.exception.EntityNotFoundException;
import com.example.lifeos.mapper.HabitMapper;
import com.example.lifeos.repository.HabitRepository;
import com.example.lifeos.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class HabitService {

    private final HabitRepository habitRepository;
    private final UserRepository userRepository;
    private final HabitMapper mapper;

    public Page<HabitSummaryDto> readAll(UUID userId, Pageable pageable) {
        findUserOrThrow(userId);
        Page<Habit> habits = habitRepository.findAllByUserId(userId, pageable);

        return habits.map(mapper::toSummaryDto);
    }

    public HabitDetailDto read(UUID userId, UUID habitId) {
        User user = findUserOrThrow(userId);
        Habit habit = findHabitOrThrow(habitId);
        checkIfHabitBelongsToUserOrThrow(habit, userId);
        return mapper.toDetailDto(habit);
    }

    public HabitDetailDto create(UUID userId, HabitCreateDto dto) {
        User user = findUserOrThrow(userId);
        Habit habit = mapper.toEntity(dto);
        habit.setUser(user);
        habitRepository.save(habit);
        return mapper.toDetailDto(habit);
    }

    @Transactional
    public HabitDetailDto update(UUID userId, UUID habitId, HabitUpdateDto dto) {
        findUserOrThrow(userId);
        Habit habit = findHabitOrThrow(habitId);
        checkIfHabitBelongsToUserOrThrow(habit, userId);
        mapper.updateEntityFromDto(dto, habit);
        habitRepository.save(habit);
        return mapper.toDetailDto(habit);
    }

    public void delete(UUID userId, UUID habitId) {
        findUserOrThrow(userId);
        Habit habit = findHabitOrThrow(habitId);
        checkIfHabitBelongsToUserOrThrow(habit, userId);
        habitRepository.deleteById(habitId);
    }


    private User findUserOrThrow(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(userId));
    }
    private Habit findHabitOrThrow(UUID habitId) {
        return habitRepository.findById(habitId)
                .orElseThrow(() -> new EntityNotFoundException(habitId));
    }
    private void checkIfHabitBelongsToUserOrThrow(Habit habit, UUID userId){
        if(!habit.getUser().getId().equals(userId)) {
            throw new RuntimeException("This habit does not belong to this User");
        }
    }


}
