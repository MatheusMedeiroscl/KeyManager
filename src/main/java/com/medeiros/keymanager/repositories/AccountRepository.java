package com.medeiros.keymanager.repositories;

import com.medeiros.keymanager.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    public Optional<AccountEntity> findByWebName(String webName);

    List<AccountEntity> findByClientId(Long clientId);
}
