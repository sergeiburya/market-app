CREATE TABLE categories
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255),
    CONSTRAINT pk_categories PRIMARY KEY (id)
);

CREATE TABLE products
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    title       VARCHAR(255),
    price       DECIMAL,
    category_id BIGINT,
    CONSTRAINT pk_products PRIMARY KEY (id)
);

ALTER TABLE products
    ADD CONSTRAINT FK_PRODUCTS_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES categories (id);
