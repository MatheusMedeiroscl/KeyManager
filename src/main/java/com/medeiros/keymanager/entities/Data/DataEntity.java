package com.medeiros.keymanager.entities.Data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.medeiros.keymanager.entities.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "data")
public class DataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "website_name", unique = true, nullable = false)
    private String websiteName;

    @Column(name = "website_url", nullable = false)
    private String websiteUrl;

    @Column(name = "registered_email", nullable = false)
    private String registeredEmail;

    @Column(name = "registered_password", nullable = false)
    private String registeredPassword;

    @Column(name = "favorite")
    private Boolean favorite;

    public boolean isFavorite() {
        return favorite;
    }


    @ManyToOne
    @JoinColumn(name = "id_user")
    @JsonBackReference //tranfere os dados da table filho para a table pai
    private UserEntity user;

    public DataEntity(DataRequestDTO dto, UserEntity user){
        this.websiteName = dto.websiteName();
        this.websiteUrl = dto.websiteUrl();
        this.registeredEmail = dto.registeredEmail();
        this.registeredPassword = dto.registeredPassword();
        this.user = user;
        this.favorite = false;
    }

}
