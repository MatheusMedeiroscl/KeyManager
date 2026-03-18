package com.medeiros.keymanager.services;

import com.medeiros.keymanager.entities.UserEntity;
import com.medeiros.keymanager.entities.UserRequestDTO;
import com.medeiros.keymanager.entities.UserResponseDTO;
import com.medeiros.keymanager.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository repository;
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository repository, BCryptPasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public UserResponseDTO findById(UserRequestDTO dto){
        UUID id = dto.id();
        UserEntity user = repository.findById(id).orElseThrow(() -> new RuntimeException(
                "[USER NOT FOUND]: ID"
        ));
        return new UserResponseDTO(user);
    }

    public UserResponseDTO create(UserRequestDTO dto){
        String encrypitPass = encoder.encode(dto.password());
        UserEntity user = new UserEntity(dto, encrypitPass);

        return new UserResponseDTO(user);
    }

    public UserResponseDTO update(UserRequestDTO dto){
        UserEntity user = repository.findById(dto.id()).orElseThrow(() -> new RuntimeException(
                "[USER NOT FOUND]: ID FOR UPDATE"
        ));

        if (dto.email() != null){user.setEmail(dto.email());};
        if (dto.name() != null){user.setName(dto.name());}
        if (dto.password() != null){ user.setPassword(encoder.encode(dto.password()));};

        repository.save(user);
        return new UserResponseDTO(user);
    };

    public void deleteClient(UserRequestDTO dto){
        UUID id = dto.id();
        repository.deleteById(id);
    };
}
