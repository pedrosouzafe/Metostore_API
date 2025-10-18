package com.metostore.api_rest.controllers;

import com.metostore.api_rest.models.Product.dto.DadosAtualizacaoProduct;
import com.metostore.api_rest.models.Product.dto.DadosCadastroProduct;
import com.metostore.api_rest.models.Product.dto.DadosDetalhamentoProduct;
import com.metostore.api_rest.models.Product.dto.DadosListagemProducts;
import com.metostore.api_rest.repositories.ProductRepository;
import com.metostore.api_rest.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/products")
public class ProductController {

    // Repository
    @Autowired
    private ProductRepository productRepository;

    // Service
    @Autowired
    private ProductService productService;


    // Endpoints
    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoProduct> cadastrarProduto(@RequestBody @Valid DadosCadastroProduct json, UriComponentsBuilder uriBuilder) {
        return productService.cadastrarProduto(json, uriBuilder);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemProducts>> listarProdutos(@PageableDefault(size = 20, sort = {"name"}) Pageable paginacao) {
        return productService.listarProdutos(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoProduct> detalharProduto(@PathVariable Long id) {
        return productService.detalharProduto(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoProduct> atualizarProduto(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoProduct json) {
        return  productService.atualizarProduto(id, json);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity removerProduto(@PathVariable Long id) {
        return productService.removerProduto(id);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<DadosListagemProducts>> listarProdutosPorBusca(
            @PageableDefault(size = 10, sort = {"name"}) Pageable paginacao,
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) Integer minRate,
            @RequestParam(required = false) Integer maxRate
            ) {

        return productService.listarProdutosPorBusca(paginacao, query, category, minPrice, maxPrice, minRate, maxRate);
    }
}
