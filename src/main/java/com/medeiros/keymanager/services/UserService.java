package com.medeiros.keymanager.services;

import com.medeiros.keymanager.entities.user.UserEntity;
import com.medeiros.keymanager.entities.user.UserRequestDTO;
import com.medeiros.keymanager.entities.user.UserResponseDTO;
import com.medeiros.keymanager.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public UserResponseDTO findById(Long id){
        UserEntity user = repository.findById(id).orElseThrow(() -> new RuntimeException(
                "[USER NOT FOUND]: ID"
        ));
        return new UserResponseDTO(user);
    }

    public UserResponseDTO create(UserRequestDTO dto){
        String encrypitPass = encoder.encode(dto.password());
        UserEntity user = new UserEntity(dto, encrypitPass);

        this.repository.save(user);

        return new UserResponseDTO(user);
    }

    public UserResponseDTO update(UserRequestDTO dto, Long id){
        UserEntity user = repository.findById(id).orElseThrow(() -> new RuntimeException(
                "[USER NOT FOUND]: ID FOR UPDATE"
        ));

        if (dto.email() != null){user.setEmail(dto.email());};
        if (dto.name() != null){user.setName(dto.name());}
        if (dto.password() != null){ user.setPassword(encoder.encode(dto.password()));};

        repository.save(user);
        return new UserResponseDTO(user);
    };

    public void delete(Long id){
        repository.deleteById(id);
    };
}
