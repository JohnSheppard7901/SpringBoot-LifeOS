package com.example.lifeos.controller;

import com.example.lifeos.service.HabitLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users/{userId}/habits/{habitId}/logs")
@RequiredArgsConstructor
public class HabitLogController {
    private final HabitLogService logService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(@PathVariable UUID userId, @PathVariable UUID habitId){
        logService.create(userId, habitId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{logId}")
    public void delete(@PathVariable UUID logId){
        logService.delete(logId);
    }
}



//TODO: api gateway, auth auslagern, keyClock