package com.example.lifeos.controller;

import com.example.lifeos.dto.userDtos.UserCreateDto;
import com.example.lifeos.dto.userDtos.UserResponseDto;
import com.example.lifeos.dto.userDtos.UserUpdateDto;
import com.example.lifeos.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping
    public ResponseEntity<Page<UserResponseDto>> readAll(@Parameter(hidden = true) Pageable pageable){
        Page<UserResponseDto> todos = service.readAll(pageable);
        return ResponseEntity.ok(todos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> read(@PathVariable UUID id){
        return ResponseEntity.ok(service.read(id));
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> create(@Valid @RequestBody UserCreateDto dto){
        return ResponseEntity.ok(service.create(dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> update(@PathVariable UUID id, @Valid @RequestBody UserUpdateDto dto){
        return ResponseEntity.ok(service.update(id, dto));
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{userId}/profile-pic")
    public void uploadProfilePic(@PathVariable UUID userId, @RequestParam("file") MultipartFile multipartFile){
        service.uploadProfilePicture(userId, multipartFile);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){
        service.delete(id);
    }


    @GetMapping("/search")
    public ResponseEntity<Page<UserResponseDto>> searchUsers(@RequestParam String username, @Parameter(hidden = true) Pageable pageable){
        return ResponseEntity.ok(service.searchUserByName(username, pageable));
    }


}
