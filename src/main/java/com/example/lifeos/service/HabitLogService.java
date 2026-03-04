package com.example.lifeos.service;

import com.example.lifeos.entity.Habit;
import com.example.lifeos.entity.HabitLog;
import com.example.lifeos.entity.User;
import com.example.lifeos.exception.EntityNotFoundException;
import com.example.lifeos.repository.HabitLogRepository;
import com.example.lifeos.repository.HabitRepository;
import com.example.lifeos.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HabitLogService {
    private final HabitLogRepository logRepository;
    private final HabitRepository habitRepository;
    private final UserRepository userRepository;

    public void create(UUID userId, UUID habitId) {
        findUserOrThrow(userId);
        Habit habit = findHabitOrThrow(habitId);
        checkIfHabitBelongsToUserOrThrow(habit, userId);

        HabitLog habitLog = new HabitLog();
        habitLog.setHabit(habit);
        logRepository.save(habitLog);
    }

    public void delete(UUID logId) {
        HabitLog habitLog = logRepository.findById(logId)
                .orElseThrow(() -> new EntityNotFoundException(logId));
        logRepository.delete(habitLog);
    }



    private Habit findHabitOrThrow(UUID habitId){
        return habitRepository.findById(habitId)
                .orElseThrow(() -> new EntityNotFoundException(habitId));
    }
    private User findUserOrThrow(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(userId));
    }
    private void checkIfHabitBelongsToUserOrThrow(Habit habit, UUID userId){
        if(!habit.getUser().getId().equals(userId))
            throw new RuntimeException("This habit does not belong to this User");
    }

}
