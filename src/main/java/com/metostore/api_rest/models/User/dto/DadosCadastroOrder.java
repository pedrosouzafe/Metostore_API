package com.metostore.api_rest.models.User.dto;

import com.metostore.api_rest.models.User.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.List;
import java.util.UUID;

public record DadosCadastroOrder(@NotNull Double total, @NotBlank UUID user, @Valid @NotBlank List<DadosItemOrder> items) {

}
