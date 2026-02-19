package com.example.lifeos.service;

import com.example.lifeos.dto.UserCreateDto;
import com.example.lifeos.dto.UserResponseDto;
import com.example.lifeos.dto.UserUpdateDto;
import com.example.lifeos.entity.User;
import com.example.lifeos.exception.EntityNotFoundException;
import com.example.lifeos.mapper.UserMapper;
import com.example.lifeos.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final UserMapper mapper;

    public Page<UserResponseDto> readAll(Pageable pageable){
        Page<User> users = repository.findAll(pageable);
        return users.map(mapper::toResponseDto);
    }

    public UserResponseDto read(UUID id){
        User user = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(id)
        );
        return mapper.toResponseDto(user);
    }

    public UserResponseDto create(UserCreateDto dto){
        User user = new User();
        user.setEmail(dto.email());
        user.setName(dto.name());
        user.setPassword(dto.password());

        User savedUser = repository.save(user);

        return mapper.toResponseDto(savedUser);
    }

    @Transactional
    public UserResponseDto update(UUID id, UserUpdateDto dto){
        User user = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(id)
        );
//        if (dto.email() != null){
//            user.setEmail(dto.email());
//        }
//        if (dto.name() != null){
//            user.setName(dto.name());
//        }
//        if(dto.password() != null){
//            user.setPassword(dto.password());
//        }
//        User updatedUser = repository.save(user);

        mapper.updateEntityFromDto(dto, user);

        return mapper.toResponseDto(user);
    }

    public void delete(UUID id){
        User user = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(id)
        );
        repository.delete(user);
    }

}


























