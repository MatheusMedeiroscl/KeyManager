package com.medeiros.keymanager.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Accounts")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(name = "web_name")
    private String webName;

    @Column(name = "web_link")
    private String webLink;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "favorite")
    private boolean favorite;


    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonBackReference //Eu sou a tabelaFilho, portanto não mostre os dados da tabela principal
    private ClientEntity client;

}
