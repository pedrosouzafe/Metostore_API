package com.metostore.api_rest.models.User.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosLoginUser(@NotBlank @Email String email, @NotBlank String password) {
}
