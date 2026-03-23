package com.medeiros.keymanager.entities.Data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.medeiros.keymanager.entities.user.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "data")
public class DataEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "website_name")
    private String websiteName;

    @Column(name = "website_url")
    private String websiteUrl;

    @Column(name = "registered_email")
    private String registeredEmail;

    @Column(name = "registered_password")
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

    public DataEntity(DataRequestDTO dto){
        this.websiteName = dto.websiteName();
        this.websiteUrl = dto.websiteUrl();
        this.registeredEmail = dto.registeredEmail();
        this.registeredPassword = dto.registeredPassword();
        this.favorite = false;
    }

}
