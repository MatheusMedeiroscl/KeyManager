package com.medeiros.keymanager.services;

import com.medeiros.keymanager.entities.AccountEntity;
import com.medeiros.keymanager.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    public AccountEntity findById(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException(
                "[ACCOUNT NOT FOUND] ID = " + id
        ));
    };


    public AccountEntity findByName(String name){
        return repository.findByWebName(name).orElseThrow(() -> new RuntimeException(
                "[ACCOUNT NOT FOUND] NAME = " + name
        ));
    }
    public AccountEntity createAccount(AccountEntity newAccount) {
        newAccount.setId(null);
        return repository.save(newAccount);
    }

    public AccountEntity updateAccount(AccountEntity updatedAccount){
       AccountEntity account = repository.findById(updatedAccount.getId()).orElseThrow(() -> new RuntimeException(
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


