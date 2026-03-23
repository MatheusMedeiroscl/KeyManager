package com.medeiros.keymanager.services;

import com.medeiros.keymanager.entities.Data.DataEntity;
import com.medeiros.keymanager.entities.Data.DataRequestDTO;
import com.medeiros.keymanager.entities.Data.DataResponseDTO;
import com.medeiros.keymanager.entities.user.UserEntity;
import com.medeiros.keymanager.repositories.DataRepository;
import com.medeiros.keymanager.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DataService {

    private final DataRepository repository;
    private final UserRepository userRepository;
    public DataService(DataRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public DataResponseDTO findById(Long id){
        DataEntity data = repository.findById(id).orElseThrow(() -> new RuntimeException(
                "[DATA NOT FOUNDED]: ID"
        ));

        return new DataResponseDTO(data);
    };
    public DataResponseDTO create(DataRequestDTO dto) {
        UserEntity user = userRepository.findById(dto.id_user()).orElseThrow(() -> new RuntimeException(
                "[USER NOT FOUNDED]: "
        ));
        DataEntity data = new DataEntity(dto, user);
        repository.save(data);
        return new DataResponseDTO(data);
    }
    public DataResponseDTO update(DataRequestDTO dto, Long id){
        DataEntity data = repository.findById(id).orElseThrow(() -> new RuntimeException(
                "[DATA NOT FOUNDED]: ID UPDATE"
        ));

        if (dto.websiteName() != null){data.setWebsiteName(dto.websiteName());}
        if (dto.websiteUrl() != null){data.setWebsiteUrl(dto.websiteUrl());}
        if (dto.registeredEmail() != null){data.setRegisteredEmail(dto.registeredEmail());}
        if (dto.registeredPassword() != null){data.setRegisteredPassword(dto.registeredPassword());}

        data.setFavorite(!data.isFavorite());
        repository.save(data);

        return new DataResponseDTO(data);
        }

    public void delete(Long id){
        repository.deleteById(id);
    }

}


