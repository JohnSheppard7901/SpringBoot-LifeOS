package com.example.lifeos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(UUID id) {
        super("Entity with " + id + " could not be found.");
    }
    public EntityNotFoundException(String message) {
        super(message);
    }
}
