package com.medeiros.keymanager.repositories;

import com.medeiros.keymanager.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    public Optional<UserEntity> findById(UUID id);
    public Optional<UserEntity> deleteById(UUID id);

}
