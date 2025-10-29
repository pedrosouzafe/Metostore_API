package com.metostore.api_rest.models.User.dto;

import com.metostore.api_rest.models.User.OrderItem;

public record DadosItemOrder(Long id, String name, Double price, Integer quantity) {
    public DadosItemOrder(OrderItem dadosItemOrder) {
        this(dadosItemOrder.getProduct().getId(), dadosItemOrder.getName(), dadosItemOrder.getPrice(), dadosItemOrder.getQuantity());
    }
}
