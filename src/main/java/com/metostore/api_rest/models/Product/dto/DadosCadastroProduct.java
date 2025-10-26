package com.metostore.api_rest.models.Product.dto;

import com.metostore.api_rest.models.Product.Category;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DadosCadastroProduct(
        @NotBlank
        String name,

        @NotBlank
        String description,

        @NotNull
        double price,

        @NotBlank
        String brand,

        String image,

        int rate,

        @NotNull
        Category category,

        boolean available,

        int quantity,

        List<DadosCadastroProductImages> images
) {
}
