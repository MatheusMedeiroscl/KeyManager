package com.medeiros.keymanager.entities;

public record DataResponseDTO(
        String websiteName,
        String websiteUrl,
        String registeredEmail,
        String registeredPassword,
        Boolean favorite
        ) {

    public DataResponseDTO(DataEntity data){
        this(
                data.getWebsiteName(),
                data.getWebsiteUrl(),
                data.getRegisteredEmail(),
                data.getRegisteredPassword(),
                data.getFavorite()
        );
    }
}
