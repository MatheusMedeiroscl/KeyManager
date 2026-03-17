package com.medeiros.keymanager.services;

import com.medeiros.keymanager.entities.DataEntity;
import com.medeiros.keymanager.repositories.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataService {

    @Autowired
    private DataRepository repository;

    public DataEntity findById(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException(
                "[ACCOUNT NOT FOUND] ID = " + id
        ));
    };


    public DataEntity findByName(String name){
        return repository.findByWebName(name).orElseThrow(() -> new RuntimeException(
                "[ACCOUNT NOT FOUND] NAME = " + name
        ));
    }
    public DataEntity createAccount(DataEntity newAccount) {
        newAccount.setId(null);
        return repository.save(newAccount);
    }

    public DataEntity updateAccount(DataEntity updatedAccount){
       DataEntity account = repository.findById(updatedAccount.getId()).orElseThrow(() -> new RuntimeException(
                "[ACCOUNT NOT FOUND, CAN`T UPDATADE DATA] ID = " + updatedAccount.getId()));

        if (updatedAccount.getWebName() != null){account.setWebName(updatedAccount.getWebName());}
        if (updatedAccount.getWebLink() != null){account.setWebLink(updatedAccount.getWebLink());}
        if (updatedAccount.getEmail() != null){account.setEmail(updatedAccount.getEmail());}
        if (updatedAccount.getPassword() != null){account.setPassword(updatedAccount.getPassword());}
        account.setFavorite(updatedAccount.isFavorite());

        return repository.save(account);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

}


