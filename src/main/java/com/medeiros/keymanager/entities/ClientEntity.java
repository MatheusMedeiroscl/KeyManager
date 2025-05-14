package com.medeiros.keymanager.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @Column(name = "client_id", nullable = false)
    private Long id;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)

    private String password;

    @OneToMany( mappedBy = "client",cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY) //Um client relacionado a várias colunas
    @JsonManagedReference // eu sou a Tabala principal, portanto me mostre os dados da tabela filho
    private List<AccountEntity> clientAccounts;
}
