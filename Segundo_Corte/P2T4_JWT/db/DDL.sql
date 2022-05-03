CREATE TABLE clients (
    client_id SERIAL NOT NULL,
    client_name VARCHAR(200) NOT NULL,
    client_last_name VARCHAR(200) NOT NULL,
    client_document VARCHAR(50) NOT NULL,
    client_phone INT NOT NULL,
    CONSTRAINT PK_CLIENTS PRIMARY KEY (client_id)
);


CREATE TABLE providers (
    provider_id SERIAL NOT NULL,
    provider_name VARCHAR(200) NOT NULL,
    provider_nit VARCHAR(200) NOT NULL,
    provider_address VARCHAR(200) NOT NULL,
    CONSTRAINT PK_PROVIDERS PRIMARY KEY (provider_id)
);


CREATE TABLE products (
    product_id SERIAL NOT NULL,
    provider_id INT4 NOT NULL,
    product_name VARCHAR(50) NOT NULL,
    product_description VARCHAR(50) NOT NULL,
    product_stock INT NOT NULL,
    product_price INT NOT NULL,
    product_iva FLOAT NOT NULL,
    CONSTRAINT PK_PRODUCTS PRIMARY KEY (product_id),
    CONSTRAINT FK_PRODUCTS_PROVIDER FOREIGN KEY (provider_id) REFERENCES providers (provider_id) ON UPDATE CASCADE ON DELETE RESTRICT
);


CREATE TABLE orders (
    order_id SERIAL NOT NULL,
    product_id INT4 NOT NULL,
    order_reference VARCHAR(50) NOT NULL,
    order_description VARCHAR(200) NOT NULL,
    CONSTRAINT PK_ORDERS PRIMARY KEY (order_id),
    CONSTRAINT FK_ORDERS_PRODUCTS FOREIGN KEY (product_id) REFERENCES products (product_id) ON UPDATE CASCADE ON DELETE RESTRICT
);


CREATE TABLE order_details (
    order_detail_id SERIAL NOT NULL,
    order_id INT4 NOT NULL,
    order_detail_address VARCHAR(200) NOT NULL,
    order_detail_total FLOAT NOT NULL,
    CONSTRAINT PK_ORDER_DETAILS PRIMARY KEY (order_detail_id),
    CONSTRAINT FK_ORDER_DETAILS_ORDERS FOREIGN KEY (order_id) REFERENCES orders (order_id) ON UPDATE CASCADE ON DELETE RESTRICT
);


CREATE TABLE order_client (
    order_client_id SERIAL NOT NULL,
    order_id INT4 NOT NULL,
    client_id INT4 NOT NULL,
    order_client_description VARCHAR(200) NOT NULL,
    CONSTRAINT PK_ORDER_CLIENT PRIMARY KEY (order_client_id),
    CONSTRAINT FK_ORDER_CLIENT_ORDERS FOREIGN KEY (order_id) REFERENCES orders (order_id) ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT FK_ORDER_CLIENT_CLIENTS FOREIGN KEY (client_id) REFERENCES clients (client_id) ON UPDATE CASCADE ON DELETE RESTRICT
);


CREATE TABLE invoices (
    invoice_id SERIAL NOT NULL,
    product_id INT4 NOT NULL,
    invoice_date DATE NOT NULL,
    invoice_total FLOAT NOT NULL,
    CONSTRAINT PK_INVOICES PRIMARY KEY (invoice_id),
    CONSTRAINT FK_INVOICES_PRODUCTS FOREIGN KEY (product_id) REFERENCES products (product_id) ON UPDATE CASCADE ON DELETE RESTRICT
);

