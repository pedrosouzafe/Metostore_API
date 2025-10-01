package com.metostore.api_rest.models.Product.dto;

import com.metostore.api_rest.models.Product.Category;

public record DadosAtualizacaoProduct(String name, String description, double price, String image, Category category, String brand, int rate, int quantity, boolean active) {
}
