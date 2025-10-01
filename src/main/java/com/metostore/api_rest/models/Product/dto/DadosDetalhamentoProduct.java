package com.metostore.api_rest.models.Product.dto;

import com.metostore.api_rest.models.Product.Category;
import com.metostore.api_rest.models.Product.Product;

public record DadosDetalhamentoProduct(Long id, String name, String description, String brand, double price, int rate, String image, Category category, int quantity) {
    public DadosDetalhamentoProduct(Product product) {
        this(product.getId(), product.getName(), product.getDescription(), product.getBrand(), product.getPrice(), product.getRate(), product.getImage(), product.getCategory(), product.getQuantity());
    }
}
