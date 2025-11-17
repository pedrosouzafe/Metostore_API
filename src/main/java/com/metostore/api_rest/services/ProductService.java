package com.metostore.api_rest.services;

import com.metostore.api_rest.models.Product.Product;
import com.metostore.api_rest.models.Product.dto.DadosAtualizacaoProduct;
import com.metostore.api_rest.models.Product.dto.DadosCadastroProduct;
import com.metostore.api_rest.models.Product.dto.DadosDetalhamentoProduct;
import com.metostore.api_rest.models.Product.dto.DadosListagemProducts;
import com.metostore.api_rest.repositories.ProductRepository;
import com.metostore.api_rest.specifications.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;


@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    // POST /products
    public ResponseEntity<DadosDetalhamentoProduct> cadastrarProduto(DadosCadastroProduct json, UriComponentsBuilder uriBuilder) {
        var product = new Product(json);

        productRepository.save(product);

        var uri = uriBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoProduct(product));
    }

    // GET /products
    public ResponseEntity<Page<DadosListagemProducts>> listarProdutos(Pageable paginacao) {
        var product = productRepository.findAllByActiveTrue(paginacao)
                .map(DadosListagemProducts::new);

        return ResponseEntity.ok(product);
    }

    // GET /products/:id
    public ResponseEntity<DadosDetalhamentoProduct> detalharProduto(Long id) {
        var findProduct = productRepository.getReferenceById(id);

        var product = new DadosDetalhamentoProduct(findProduct);

        return ResponseEntity.ok(product);
    }

    // PUT /products/:id
    public ResponseEntity<DadosDetalhamentoProduct> atualizarProduto(Long id, DadosAtualizacaoProduct json) {
        Product product = productRepository.getReferenceById(id);
        product.atualizarProduto(json);

        return ResponseEntity.ok(new DadosDetalhamentoProduct(product));
    }

    // DELETE /products/:id
    public ResponseEntity removerProduto(Long id) {
        var product = productRepository.getReferenceById(id);
        product.inativarProduto();

        return ResponseEntity.noContent().build();
    }

    // GET /products/search?
    public ResponseEntity<Page<DadosListagemProducts>> listarProdutosPorBusca(
            Pageable paginacao,
            String query,
            String category,
            Double minPrice,
            Double maxPrice,
            Integer minRate,
            Integer maxRate,
            String order
    ) {
        // Começa sem filtro
        Specification<Product> specification = (root, queryCr, cb) -> cb.conjunction();

        if(query != null && !query.isBlank()) {
            // '.and' adiciona os filtros caso os parâmetros não estejam vazios
            specification = specification.and(ProductSpecification.nameOrDescriptionContains(query));
        }

        if(category != null && !category.isBlank()) {
            specification = specification.and(ProductSpecification.categoryEquals(category));
        }

        if(minPrice != null) {
            specification = specification.and(ProductSpecification.priceGreaterThanOrEqual(minPrice));
        }

        if(maxPrice != null) {
          specification = specification.and(ProductSpecification.priceLessThanOrEqual(maxPrice));
        }

        if(minRate != null) {
            specification = specification.and(ProductSpecification.rateGreaterThanOrEqual(minRate));
        }

        if(maxRate != null) {
          specification = specification.and(ProductSpecification.rateLessThanOrEqual(maxRate));
        }

        Pageable sortedPageable = paginacao;

        specification = specification.and(ProductSpecification.activeTrue());

        if (order != null) {
            switch (order.toLowerCase()) {
                case "price_asc":
                    sortedPageable = PageRequest.of(
                            paginacao.getPageNumber(),
                            paginacao.getPageSize(),
                            Sort.by("price").ascending()
                    );
                    break;
                case "price_desc":
                    sortedPageable = PageRequest.of(
                            paginacao.getPageNumber(),
                            paginacao.getPageSize(),
                            Sort.by("price").descending()
                    );
                    break;
                case "best_rated":
                    sortedPageable = PageRequest.of(
                            paginacao.getPageNumber(),
                            paginacao.getPageSize(),
                            Sort.by("rate").descending()
                    );
                    break;
                default:
                    break;
            }
        }

        var product = productRepository.findAll(specification, sortedPageable)
                .map(DadosListagemProducts::new);

        return ResponseEntity.ok(product);
    }
}
