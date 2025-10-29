CREATE TABLE order_items (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    price DOUBLE PRECISION,
    quantity INTEGER,
    order_id BIGINT,
    CONSTRAINT fk_order_items_order FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE
);