package com.medeiros.keymanager.entities.user;

import java.util.UUID;

public record UserResponseDTO(String email, String name) {
    public UserResponseDTO(UserEntity user){
        this(user.getEmail(), user.getName());
    }
}
