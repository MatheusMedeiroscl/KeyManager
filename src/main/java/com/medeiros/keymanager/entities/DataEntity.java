package com.medeiros.keymanager.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "data")
public class DataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private Long id;

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
    @JoinColumn(name = "id")
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
