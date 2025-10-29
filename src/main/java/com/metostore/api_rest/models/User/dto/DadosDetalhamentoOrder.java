package com.metostore.api_rest.models.User.dto;

import com.metostore.api_rest.models.User.Order;

import java.time.LocalDateTime;
import java.util.List;

public record DadosDetalhamentoOrder(Long id, Double total, DadosUserResumo user, LocalDateTime date, List<DadosItemOrder> items) {
    public DadosDetalhamentoOrder(Order order) {
        this(order.getId(), order.getTotal(), new DadosUserResumo(order.getUser()), order.getDate(), order.getItems().stream().map(DadosItemOrder::new).toList());
    }
}
