package com.example.lifeos.exception;

import java.util.UUID;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(UUID id) {
        super("Entity with " + id + " could not be found.");
    }
}
