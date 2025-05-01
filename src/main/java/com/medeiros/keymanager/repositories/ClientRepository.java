package com.medeiros.keymanager.repositories;

import com.medeiros.keymanager.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    public Optional<ClientEntity> findByLogin(String  login);
}
