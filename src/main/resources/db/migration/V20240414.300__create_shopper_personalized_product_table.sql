CREATE TABLE shopper_personalized_product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    shopper_id VARCHAR(255),
    product_id VARCHAR(255),
    relevancy_score DOUBLE,
    CONSTRAINT fk_shopper_id FOREIGN KEY (shopper_id) REFERENCES shopper (shopper_id),
    CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES product (product_id),
    UNIQUE (shopper_id, product_id, relevancy_score)
);
