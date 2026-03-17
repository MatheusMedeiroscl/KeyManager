package com.medeiros.keymanager.repositories;

import com.medeiros.keymanager.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<UserEntity, Long> {
    public Optional<UserEntity> findByLogin(String  login);
}
