package com.medeiros.keymanager.entities.Data;

import java.util.UUID;

public record DataRequestDTO(String websiteName, String websiteUrl, String registeredEmail,
                             String registeredPassword, UUID id, Boolean favorite){
}
