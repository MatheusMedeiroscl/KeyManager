package com.medeiros.keymanager.entities;

import java.util.UUID;

public record UserResponseDTO(String email, String name, UUID id) {
    public UserResponseDTO(UserEntity user){
        this(user.getEmail(), user.getName(), user.getId());
    }
}
