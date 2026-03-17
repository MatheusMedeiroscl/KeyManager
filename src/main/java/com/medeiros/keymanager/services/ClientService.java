package com.medeiros.keymanager.services;

import com.medeiros.keymanager.entities.UserEntity;
import com.medeiros.keymanager.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;
    @Autowired
    private PasswordEncoder encoder;

    public UserEntity findById(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException(
            "[CLIENT NOT FOUND] ID = " + id
        ));
    }

    public UserEntity findByLogin(String login){
        return repository.findByLogin(login).orElseThrow(() -> new RuntimeException(
                "[CLIENT NOT FOUND] LOGIN = " + login
        ));
    }

    public UserEntity createClient(UserEntity newClient){
        newClient.setId(null);
        newClient.setPassword(encoder.encode(newClient.getPassword()));
        return repository.save(newClient);
    }

    public UserEntity updateClient(UserEntity updatedClient){
        UserEntity client = findById(updatedClient.getId());

        if (updatedClient.getLogin() != null){
            client.setLogin(updatedClient.getLogin());
        };
        if (updatedClient.getPassword() != null){
            client.setPassword(encoder.encode(updatedClient.getPassword()));
        };

        return repository.save(client);
    };

    public void deleteClient(Long id){
        repository.deleteById(id);
    };


}
