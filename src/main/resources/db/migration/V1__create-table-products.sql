
CREATE TABLE products (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT NOT NULL,
    brand VARCHAR(100) NOT NULL,
    price NUMERIC(10,2) NOT NULL,
    image TEXT NOT NULL,
    rate SMALLINT,
    category VARCHAR(100) NOT NULL,
    available BOOLEAN NOT NULL DEFAULT TRUE,
    quantity NUMERIC,
    active BOOLEAN NOT NULL DEFAULT TRUE
);