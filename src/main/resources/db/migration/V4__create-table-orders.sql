CREATE TABLE orders (
    id BIGSERIAL PRIMARY KEY,
    total DOUBLE PRECISION,
    user_id UUID,
    date TIMESTAMP,
    CONSTRAINT fk_orders_user FOREIGN KEY (user_id) REFERENCES users(id)
);
