CREATE TABLE cart_items (
    id SERIAL PRIMARY KEY,
    user_id VARCHAR(255),
    product_id BIGINT,
    quantity INT,
    price NUMERIC
);