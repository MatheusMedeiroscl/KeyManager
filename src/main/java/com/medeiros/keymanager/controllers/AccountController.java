package com.medeiros.keymanager.controllers;


import com.medeiros.keymanager.entities.AccountEntity;
import com.medeiros.keymanager.services.AccountService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService service;

    @GetMapping("/{id}")
    public ResponseEntity<AccountEntity> findById(@PathVariable Long id){
        AccountEntity account = this.service.findById(id);
        return ResponseEntity.ok().body(account);
    }


    @PostMapping
    public ResponseEntity<AccountEntity> create(@RequestBody AccountEntity newAccount){
        this.service.createAccount(newAccount);

        // Monta a URI do recurso recém- criado  para retornar no cabeçalho 'Location' da resposta 201
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(newAccount.getId()).toUri();

        return  ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountEntity> update(@PathVariable Long id, @RequestBody AccountEntity updatedAccount){
        updatedAccount.setId(id);
        this.service.updateAccount(updatedAccount);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        this.service.delete(id);
    }


}
