package com.medeiros.keymanager.entities;

import java.util.UUID;

public record DataRequestDTO(String websiteName, String websiteUrl, String registeredEmail,
                             String registeredPassword, UUID id, Boolean favorite){
}
