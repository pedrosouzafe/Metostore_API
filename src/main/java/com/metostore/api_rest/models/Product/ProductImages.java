package com.metostore.api_rest.models.Product;

import com.metostore.api_rest.models.Product.dto.DadosAtualizacaoProductImages;
import com.metostore.api_rest.models.Product.dto.DadosCadastroProductImages;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "product_images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    private String altText;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    public ProductImages(DadosCadastroProductImages img) {
        this.url = img.url();
        this.altText = img.altText();
    }

    public ProductImages(DadosAtualizacaoProductImages dto) {
        this.id = dto.id();
        this.url = dto.url();
        this.altText = dto.altText();
    }
}
