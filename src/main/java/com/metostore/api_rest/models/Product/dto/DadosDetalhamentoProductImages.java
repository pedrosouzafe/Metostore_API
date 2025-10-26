package com.metostore.api_rest.models.Product.dto;

import com.metostore.api_rest.models.Product.ProductImages;

public record DadosDetalhamentoProductImages(Long id, String url, String altText, Long idProduct) {
    public DadosDetalhamentoProductImages(ProductImages img) {
        this(img.getId(), img.getUrl(), img.getAltText(), img.getProduct().getId());
    }
}
