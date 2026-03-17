package com.medeiros.keymanager.repositories;

import com.medeiros.keymanager.entities.DataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DataRepository extends JpaRepository<DataEntity, Long> {
    public Optional<DataEntity> findByWebName(String webName);

}
