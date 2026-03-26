package com.medeiros.keymanager.controllers;

import com.medeiros.keymanager.entities.user.*;
import com.medeiros.keymanager.services.TokenService;
import com.medeiros.keymanager.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, UserService userService, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO dto){
        var userNamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.password()); // cria uma chave de validação
        var auth = this.authenticationManager.authenticate(userNamePassword); // valida a senha com o user do banco
        var token = tokenService.generateToken((UserEntity) Objects.requireNonNull(auth.getPrincipal())); // cria o token com base no user encontrado

        return  ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRequestDTO dto){
        UserResponseDTO user = this.userService.create(dto);
        return  ResponseEntity.ok().body(user);
    }
}
