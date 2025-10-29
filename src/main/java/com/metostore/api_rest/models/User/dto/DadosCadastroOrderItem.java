package com.metostore.api_rest.models.User.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroOrderItem(@NotBlank String name, @NotNull Double price, @NotNull  Integer quantity) {
}
