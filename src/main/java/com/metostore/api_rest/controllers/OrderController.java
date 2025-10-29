package com.metostore.api_rest.controllers;

import com.metostore.api_rest.models.Product.Product;
import com.metostore.api_rest.models.User.Order;
import com.metostore.api_rest.models.User.OrderItem;
import com.metostore.api_rest.models.User.User;
import com.metostore.api_rest.models.User.dto.DadosCadastroOrder;
import com.metostore.api_rest.models.User.dto.DadosDetalhamentoOrder;
import com.metostore.api_rest.models.User.dto.DadosListagemOrder;
import com.metostore.api_rest.repositories.OrderRepository;
import com.metostore.api_rest.repositories.ProductRepository;
import com.metostore.api_rest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    // Criar pedido
    @PostMapping
    public ResponseEntity<DadosDetalhamentoOrder> criarOrder(
            @RequestBody DadosCadastroOrder dados,
            UriComponentsBuilder uriBuilder) {

        User user = userRepository.findById(dados.user())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Order order = new Order();
        order.setUser(user);
        order.setTotal(dados.total());


        List<OrderItem> orderItems = dados.items().stream().map(item -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setName(item.name());
            orderItem.setPrice(item.price());
            orderItem.setQuantity(item.quantity());

            var product = productRepository.getReferenceById(item.id());

            if(product.getQuantity() < item.quantity()) {
                throw new RuntimeException("Produto " + product.getName() + " não tem estoque suficiente");
            }
            product.setQuantity(product.getQuantity() - item.quantity());

            productRepository.save(product);


            orderItem.setProduct(product);

            return orderItem;
        }).toList();

        order.setItems(orderItems);
        orderRepository.save(order);

        var uri = uriBuilder.path("/orders/{id}").buildAndExpand(order.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoOrder(order));
    }


    // Listar pedidos de um usuário
    @GetMapping("/user/{userId}")
    public ResponseEntity<Page<DadosListagemOrder>> listarOrders(
            @PathVariable UUID userId,
            Pageable paginacao
    ) {
        var orders = orderRepository.findByUserId(userId, paginacao)
                .map(DadosListagemOrder::new);

        return ResponseEntity.ok(orders);
    }
}
