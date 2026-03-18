package com.medeiros.keymanager.services;

import com.medeiros.keymanager.entities.DataEntity;
import com.medeiros.keymanager.entities.DataRequestDTO;
import com.medeiros.keymanager.entities.DataResponseDTO;
import com.medeiros.keymanager.repositories.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DataService {

    private final DataRepository repository;

    public DataService(DataRepository repository) {
        this.repository = repository;
    }

    public DataResponseDTO findById(DataRequestDTO dto){
        UUID id = dto.id();

        DataEntity data = repository.findById(id).orElseThrow(() -> new RuntimeException(
                "[DATA NOT FOUNDED]: ID"
        ));

        return new DataResponseDTO(data);
    };
    public DataResponseDTO create(DataRequestDTO dto) {
        DataEntity data = new DataEntity(dto);
        return new DataResponseDTO(data);
    }



        public DataResponseDTO update(DataRequestDTO dto){
            UUID id = dto.id();
            DataEntity data = repository.findById(id).orElseThrow(() -> new RuntimeException(
                    "[DATA NOT FOUNDED]: ID UPDATE"));

            if (dto.websiteName() != null){data.setWebsiteName(dto.websiteName());}
            if (dto.websiteUrl() != null){data.setWebsiteUrl(dto.websiteUrl());}
            if (dto.registeredEmail() != null){data.setRegisteredEmail(dto.registeredEmail());}
            if (dto.registeredPassword() != null){data.setRegisteredPassword(dto.registeredPassword());}

            data.setFavorite(!data.isFavorite());
            repository.save(data);

            return new DataResponseDTO(data);
        }

    public void delete(DataRequestDTO dto){
        UUID id = dto.id();
        repository.deleteById(id);
    }

}


