package com.metostore.api_rest.models.User.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUser(@NotBlank String name, @NotBlank @Email String email, @NotBlank String password) {
}
