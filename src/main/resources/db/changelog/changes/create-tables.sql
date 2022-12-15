CREATE TABLE IF NOT EXISTS categories
(
    id bigint NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS products
(
    id bigint NOT NULL AUTO_INCREMENT,
    title varchar(255) NOT NULL,
    price decimal(10,0) NOT NULL,
    category_id bigint,
    PRIMARY KEY (id),
    CONSTRAINT category_fk FOREIGN KEY (category_id)
                           REFERENCES categories (id)
)
