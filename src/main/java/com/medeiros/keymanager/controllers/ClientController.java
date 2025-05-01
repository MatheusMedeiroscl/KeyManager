package com.medeiros.keymanager.controllers;

import com.medeiros.keymanager.entities.ClientEntity;
import com.medeiros.keymanager.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private  ClientService service;


    @GetMapping("/{id}")
    public ResponseEntity<ClientEntity> findById(@PathVariable Long id){

        ClientEntity client = this.service.findById(id);

        return ResponseEntity.ok().body(client);
    }

    @PostMapping
    public ResponseEntity<ClientEntity> create(@RequestBody ClientEntity newClient){
        this.service.createClient(newClient);

        // Monta a URI do recurso recém- criado  para retornar no cabeçalho 'Location' da resposta 201
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(newClient.getId()).toUri();

        return  ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody ClientEntity updatedClient){
        updatedClient.setId(id);
        this.service.updateClient(updatedClient);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        this.service.deleteClient(id);
    }

}
