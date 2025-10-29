package com.metostore.api_rest.models.User.dto;

import com.metostore.api_rest.models.User.User;

import java.util.UUID;

public record DadosUserResumo(UUID id, String name, String email) {
    public DadosUserResumo(User user) {
        this(user.getId(), user.getName(), user.getEmail());
    }
}
