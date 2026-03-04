package com.example.lifeos.controller;

import com.example.lifeos.dto.habitDtos.HabitCreateDto;
import com.example.lifeos.dto.habitDtos.HabitDetailDto;
import com.example.lifeos.dto.habitDtos.HabitSummaryDto;
import com.example.lifeos.dto.habitDtos.HabitUpdateDto;
import com.example.lifeos.service.HabitService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class HabitController {
    private final HabitService habitService;

    @GetMapping("/{userId}/habits")
    public ResponseEntity<Page<HabitSummaryDto>> readAll(@PathVariable UUID userId, @Parameter(hidden = true) Pageable pageable){
        return ResponseEntity.ok(habitService.readAll(userId, pageable));
    }

    @GetMapping("/{userId}/habits/{habitId}")
    public ResponseEntity<HabitDetailDto> read(@PathVariable UUID userId, @PathVariable UUID habitId){
        return ResponseEntity.ok(habitService.read(userId, habitId));
    }

    @PostMapping("/{userId}/habits")
    public ResponseEntity<HabitDetailDto> create(@PathVariable UUID userId, @Valid @RequestBody HabitCreateDto dto){
        return ResponseEntity.ok(habitService.create(userId, dto));
    }

    @PatchMapping("/{userId}/habits/{habitId}")
    public ResponseEntity<HabitDetailDto> update(@PathVariable UUID userId, @PathVariable UUID habitId, @Valid @RequestBody HabitUpdateDto dto){
        return ResponseEntity.ok(habitService.update(userId, habitId, dto));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{userId}/habits/{habitId}")
    public void delete(@PathVariable UUID userId, @PathVariable UUID habitId){
        habitService.delete(userId, habitId);
    }

}
