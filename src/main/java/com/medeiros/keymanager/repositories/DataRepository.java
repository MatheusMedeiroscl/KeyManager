package com.medeiros.keymanager.repositories;

import com.medeiros.keymanager.entities.Data.DataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DataRepository extends JpaRepository<DataEntity, Long> {
    public DataEntity findByWebsiteName(String websiteName);
}
