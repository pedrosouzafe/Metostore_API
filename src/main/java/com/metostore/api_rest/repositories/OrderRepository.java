package com.metostore.api_rest.repositories;

import com.metostore.api_rest.models.User.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findByUserId(UUID userId, Pageable paginacao);
}
