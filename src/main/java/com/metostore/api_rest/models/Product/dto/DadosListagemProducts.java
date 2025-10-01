package com.metostore.api_rest.models.Product.dto;

import com.metostore.api_rest.models.Product.Category;
import com.metostore.api_rest.models.Product.Product;

public record DadosListagemProducts(Long id, String name, double price, int rate, String image, Category category) {
    public DadosListagemProducts(Product product) {
        this(product.getId(), product.getName(), product.getPrice(), product.getRate(), product.getImage(), product.getCategory());
    }
}
