package com.medeiros.keymanager.repositories;

import com.medeiros.keymanager.entities.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    public Optional<UserEntity> findByEmail(String email);

}
