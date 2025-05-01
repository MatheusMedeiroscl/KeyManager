package com.medeiros.keymanager.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "Client")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id", nullable = false)
    private Long id;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany( mappedBy = "client",cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY) //Um client relacionado a várias colunas
    private List<AccountEntity> clientAccounts;
}
