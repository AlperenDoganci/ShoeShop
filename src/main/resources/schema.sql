DROP TABLE IF EXISTS customers CASCADE;
CREATE TABLE customers
(
 --    customer_id     INT AUTO_INCREMENT PRIMARY KEY,
    customer_id     SERIAL PRIMARY KEY,
    first_name      VARCHAR(100) NOT NULL,
    last_name       VARCHAR(100) NOT NULL,
    email           VARCHAR(100) NOT NULL,
    phone_number    VARCHAR(100),
    date_of_birth   DATE,
    gender          VARCHAR(2)
);

DROP TABLE IF EXISTS shoes CASCADE;
CREATE TABLE shoes
(
 --    shoe_id         INT AUTO_INCREMENT PRIMARY KEY,
    shoe_id         SERIAL PRIMARY KEY,
    brand           VARCHAR(100) NOT NULL,
    size            DOUBLE PRECISION NOT NULL,
    color           VARCHAR(100) NOT NULL,
    price           DOUBLE PRECISION NOT NULL
);

DROP TABLE IF EXISTS purchases CASCADE;
CREATE TABLE purchases
(
--    purchase_id     INT AUTO_INCREMENT PRIMARY KEY,
    purchase_id     SERIAL PRIMARY KEY,
    purchase_date   DATE NOT NULL,
    total_price     DOUBLE PRECISION NOT NULL,
    payment_method  VARCHAR(100) NOT NULL,
    customer_id     INT,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

DROP TABLE IF EXISTS purchase_shoe CASCADE;
CREATE TABLE purchase_shoe
(
    purchase_id     INT,
    shoe_id         INT,
    --DELETE THE LINE BELOW WHEN RUNNING JDBC
    PRIMARY KEY (purchase_id, shoe_id),
    FOREIGN KEY (purchase_id) REFERENCES purchases(purchase_id),
    FOREIGN KEY (shoe_id) REFERENCES shoes(shoe_id)
);

DROP TABLE IF EXISTS customer_shoe CASCADE;
CREATE TABLE customer_shoe
(
    customer_id     INT,
    shoe_id         INT,
    --DELETE THE LINE BELOW WHEN RUNNING JDBC
    PRIMARY KEY (customer_id, shoe_id),
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id),
    FOREIGN KEY (shoe_id) REFERENCES shoes(shoe_id)
);

select * from customers;
select * from shoes;
select * from purchases;

select * from customer_shoe;
select * from purchase_shoe;