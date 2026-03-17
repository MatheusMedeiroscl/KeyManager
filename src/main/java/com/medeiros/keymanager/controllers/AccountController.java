package com.medeiros.keymanager.controllers;


import com.medeiros.keymanager.entities.DataEntity;
import com.medeiros.keymanager.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService service;

    @GetMapping("/{id}")
    public ResponseEntity<DataEntity> findById(@PathVariable Long id){
        DataEntity account = this.service.findById(id);
        return ResponseEntity.ok().body(account);
    }


    @PostMapping
    public ResponseEntity<DataEntity> create(@RequestBody DataEntity newAccount){
        this.service.createAccount(newAccount);

        // Monta a URI do recurso recém- criado  para retornar no cabeçalho 'Location' da resposta 201
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(newAccount.getId()).toUri();

        return  ResponseEntity.created(uri).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DataEntity> update(@PathVariable Long id, @RequestBody DataEntity updatedAccount){
        updatedAccount.setId(id);
        DataEntity updated = this.service.updateAccount(updatedAccount);
        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        this.service.delete(id);
    }


}
