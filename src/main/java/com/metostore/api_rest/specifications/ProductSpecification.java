package com.metostore.api_rest.specifications;

import com.metostore.api_rest.models.Product.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    // Filtra pelo nome ou descrição
    public static Specification<Product> nameOrDescriptionContains(String keyword) {
        return (root, query, cb) -> cb.or(
                cb.like(cb.lower(root.get("name")), "%" + keyword.toLowerCase() + "%"),
                cb.like(cb.lower(root.get("description")), "%" + keyword.toLowerCase() + "%")
        );

        // Funciona como um where no sql, onde ele irá pesquisar '%{keyword}%' no banco tanto para a coluna 'name' quanto 'description'
    }

    // Filtra pela categoria
    public static Specification<Product> categoryEquals(String category) {
        return (root, query, cb) -> cb.equal(root.get("category"), category.toUpperCase());
    }

    // Filtra pelo preço (maior ou igual a)
    public static Specification<Product> priceGreaterThanOrEqual(Double minPrice) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    // Filtra pelo preço (menor ou igual a)
    public static Specification<Product> priceLessThanOrEqual(Double maxPrice) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("price"), maxPrice);
    }

    // Filtra pela avaliação (maior ou igual a)
    public static Specification<Product> rateGreaterThanOrEqual(int minRate) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("rate"), minRate);
    }

    // Filtra pela avaliação (menor ou igual a)
    public static Specification<Product> rateLessThanOrEqual(int maxRate) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("rate"), maxRate);
    }

    public static Specification<Product> activeTrue() {
        return (root, query, cb) -> cb.equal(root.get("active"), true);
    }
}
