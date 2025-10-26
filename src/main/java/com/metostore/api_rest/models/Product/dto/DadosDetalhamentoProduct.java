package com.metostore.api_rest.models.Product.dto;

import com.metostore.api_rest.models.Product.Category;
import com.metostore.api_rest.models.Product.Product;
import java.util.List;
import java.util.stream.Collectors;

public record DadosDetalhamentoProduct(
        Long id,
        String name,
        String description,
        String brand,
        double price,
        int rate,
        String image,
        Category category,
        int quantity,
        Boolean available,
        List<DadosDetalhamentoProductImages> images
) {
    public DadosDetalhamentoProduct(Product product) {
        this(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getBrand(),
                product.getPrice(),
                product.getRate(),
                product.getImage(),
                product.getCategory(),
                product.getQuantity(),
                product.getAvailable(),
                product.getImages() != null
                        ? product.getImages().stream()
                        .map(DadosDetalhamentoProductImages::new)
                        .collect(Collectors.toList())
                        : List.of()
        );
    }
}
