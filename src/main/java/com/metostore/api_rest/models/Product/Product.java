package com.metostore.api_rest.models.Product;

import com.metostore.api_rest.models.Product.dto.DadosAtualizacaoProduct;
import com.metostore.api_rest.models.Product.dto.DadosCadastroProduct;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "products")
@Entity(name = "Product")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false )
    private double price;

    @Column(nullable = false )
    private String brand;

    @Column(columnDefinition = "TEXT")
    private String image;

    private int rate;

    @Enumerated(EnumType.STRING )
    private Category category;

    private Boolean available = true;

    private int quantity;

    private Boolean active = true;

    public Product(DadosCadastroProduct json) {
        this.name = json.name();
        this.description = json.description();
        this.price = json.price();
        this.brand = json.brand();
        this.image = json.image();
        this.rate = json.rate();
        this.category = json.category();
        this.available = json.available();
        this.quantity = json.quantity();
    }

    public void atualizarProduto(DadosAtualizacaoProduct json) {
        if(json.name() != null) {
            this.name = json.name();
        }

        if(json.description() != null) {
            this.description = json.description();
        }

        if(json.price() >= 1) {
            this.price = json.price();
        }

        if(json.brand() != null) {
            this.brand = json.brand();
        }

        if(json.image() != null) {
            this.image = json.image();
        }

        if(json.rate() >= 1) {
            this.rate = json.rate();
        }

        if(json.category() != null) {
            this.category = json.category();
        }

        if(json.quantity() >= 1) {
            this.quantity = json.quantity();
        }

        if(json.active() == false || json.active() == true) {
            this.active = json.active();
        }

        if(!(json.quantity() > 0)) {
            this.available = false;
        }
    }

    public void inativarProduto() {
        this.active = false;
    }
}
