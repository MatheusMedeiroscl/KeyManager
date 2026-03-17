package com.medeiros.keymanager.entities;

import java.util.UUID;

public record UserRequestDTO(String email, String password, String name, UUID id) {
}
