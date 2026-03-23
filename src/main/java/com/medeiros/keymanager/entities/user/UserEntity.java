package com.medeiros.keymanager.entities.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.medeiros.keymanager.entities.Data.DataEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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


    public UserEntity(UserRequestDTO dto, String password){
        this.email = dto.email();
        this.name = dto.name();
        this.password = password;
    }

}
