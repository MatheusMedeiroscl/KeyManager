package com.medeiros.keymanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<AccountsRepository, Long> {
    public Optional<AccountsRepository> findByWebName(String webName);

    public Optional<AccountsRepository> findByClientId(Long clientID);

}
