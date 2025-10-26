CREATE TABLE product_images (
    id BIGSERIAL PRIMARY KEY,
    url TEXT NOT NULL,
    altText VARCHAR(100),
    product_id BIGINT NOT NULL,
        CONSTRAINT fk_product
            FOREIGN KEY (product_id)
            REFERENCES products (id)
            ON DELETE CASCADE
);