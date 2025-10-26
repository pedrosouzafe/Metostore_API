package com.metostore.api_rest.models.Product.dto;

import com.metostore.api_rest.models.Product.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroProductImages (
        @NotBlank
        String url,

        String altText
){
}
