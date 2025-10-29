package com.metostore.api_rest.models.User.dto;

import com.metostore.api_rest.models.User.User;

import java.util.UUID;

public record DadosRetornoUser(UUID id, String name, String email) {
    public DadosRetornoUser(User user) {
        this(
            user.getId(),
            user.getName(),
            user.getEmail()
        );
    }
}
