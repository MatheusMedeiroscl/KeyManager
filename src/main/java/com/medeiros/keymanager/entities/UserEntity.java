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
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    //Um client relacionado a várias colunas
    @OneToMany( mappedBy = "user",cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JsonManagedReference // Mostra os dados da table pai para a table filho
    private List<DataEntity> userData;
}
