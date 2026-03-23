package com.medeiros.keymanager.controllers;

import com.medeiros.keymanager.entities.user.*;
import com.medeiros.keymanager.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id){
        UserResponseDTO user = service.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping()
    public ResponseEntity<UserResponseDTO> create(@RequestBody UserRequestDTO dto){
        UserResponseDTO user = this.service.create(dto);
        return  ResponseEntity.ok().body(user);
    }

    @PostMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(@RequestBody UserRequestDTO dto, @PathVariable Long id){
        UserResponseDTO user = this.service.update(dto, id);
        return  ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        this.service.delete(id);
    }

}
