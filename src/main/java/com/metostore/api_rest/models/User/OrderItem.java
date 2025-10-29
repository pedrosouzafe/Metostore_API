    package com.metostore.api_rest.models.User;

    import com.metostore.api_rest.models.Product.Product;
    import com.metostore.api_rest.models.User.dto.DadosItemOrder;
    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    @Entity
    @Table(name = "order_items")
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public class OrderItem {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;
        private Double price;
        private Integer quantity;

        @ManyToOne
        @JoinColumn(name = "product_id")  // cria a coluna FK na tabela order_items
        private Product product;
    }
