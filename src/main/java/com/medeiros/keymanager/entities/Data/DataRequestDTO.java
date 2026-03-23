package com.medeiros.keymanager.entities.Data;

import java.util.UUID;

public record DataRequestDTO(String websiteName, String websiteUrl, String registeredEmail,
                             String registeredPassword, Boolean favorite, Long id_user){
}
