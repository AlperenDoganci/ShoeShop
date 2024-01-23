INSERT INTO customers (first_name, last_name, email, phone_number, date_of_birth, gender)
VALUES ('Michael', 'Scott', 'michaelscott@gmail.com', '123456789', '1972-10-01', 'M');
INSERT INTO customers (first_name, last_name, email, phone_number, date_of_birth, gender)
VALUES ('Jim', 'Halpert', 'jimhalpert@gmail.com', '545632189', '1982-09-02', 'M');
INSERT INTO customers (first_name, last_name, email, phone_number, date_of_birth, gender)
VALUES ('Dwight', 'Baker', 'dwightbaker@gmail.com', '456789123', '1976-12-24', 'M');
INSERT INTO customers (first_name, last_name, email, phone_number, date_of_birth, gender)
VALUES ('Angela', 'Johnson', 'angelajohnson@gmail.com', '756984156', '1974-08-06', 'F');
INSERT INTO customers (first_name, last_name, email, phone_number, date_of_birth, gender)
VALUES ('Pam', 'Beesly', 'pambeesly@outlook.org', '493567865','1984-06-12', 'F');

INSERT INTO shoes (brand, size, color, price)
VALUES ('Nike', 42, 'Black', 120);
INSERT INTO shoes (brand, size, color, price)
VALUES ('Adidas', 43, 'White', 100);
INSERT INTO shoes (brand, size, color, price)
VALUES ('Puma', 42, 'Red', 90);
INSERT INTO shoes (brand, size, color, price)
VALUES ('Nike', 36, 'Orange', 100);
INSERT INTO shoes (brand, size, color, price)
VALUES ('Cat', 42, 'Brown', 200);

INSERT INTO purchases (purchase_date, total_price, payment_method, customer_id)
VALUES ('2023-12-10', 120.0, 'CREDIT_CARD', 1);
INSERT INTO purchases (purchase_date, total_price, payment_method, customer_id)
VALUES ('2023-10-10', 200.0, 'CREDIT_CARD', 1);
INSERT INTO purchases (purchase_date, total_price, payment_method, customer_id)
VALUES ('2023-12-9', 100.0, 'PAYPAL', 2);
insert into purchases (purchase_date, total_price, payment_method, customer_id)
values ('2023-12-5', 90.0, 'PAYPAL', 2);
INSERT INTO purchases (purchase_date, total_price, payment_method, customer_id)
VALUES ('2023-12-5', 90.0, 'CASH', 3);
INSERT INTO purchases (purchase_date, total_price, payment_method, customer_id)
VALUES ('2023-11-1', 90.0, 'CREDIT_CARD', 4);
INSERT INTO purchases (purchase_date, total_price, payment_method, customer_id)
VALUES ('2023-12-10', 100.0, 'CREDIT_CARD', 4);


INSERT INTO purchase_shoe (purchase_id, shoe_id)
VALUES (1, 1); -- Michael bought Nike 120$
INSERT INTO purchase_shoe (purchase_id, shoe_id)
VALUES (2, 5); -- Michael bought Cat 200$
insert into purchase_shoe (purchase_id, shoe_id)
values (3, 2); -- Jim bought Adidas 100$
insert into purchase_shoe (purchase_id, shoe_id)
values (4,3); -- Jim bought Puma 90$
insert into purchase_shoe (purchase_id, shoe_id)
values (5,3); -- Dwight bought Puma 90$
insert into purchase_shoe (purchase_id, shoe_id)
values (6,3); -- Angela bought Puma 90$
insert into purchase_shoe (purchase_id, shoe_id)
values (7,4); -- Angela bought Nike 100$

INSERT INTO customer_shoe (customer_id, shoe_id)
VALUES (1, 1); -- Michael bought Nike 120$
INSERT INTO customer_shoe (customer_id, shoe_id)
VALUES (1, 5); -- Michael bought Cat 200$
insert into customer_shoe (customer_id, shoe_id)
values (2, 2); -- Jim bought Adidas 100$
INSERT INTO customer_shoe (customer_id, shoe_id)
VALUES (2, 3); -- Jim bought Puma 90$
INSERT INTO customer_shoe (customer_id, shoe_id)
VALUES (3, 3); -- Dwight bought Puma 90$
INSERT INTO customer_shoe (customer_id, shoe_id)
VALUES (4, 3); -- Angela bought Puma 90$
INSERT INTO customer_shoe (customer_id, shoe_id)
VALUES (4, 4); -- Angela bought Nike 100$

