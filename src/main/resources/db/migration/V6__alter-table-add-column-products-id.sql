ALTER TABLE order_items
ADD COLUMN product_id BIGINT;

ALTER TABLE order_items
ADD CONSTRAINT fk_orderitem_product
FOREIGN KEY (product_id)
REFERENCES products(id);
