INSERT INTO brand(name) VALUES('My brand');

INSERT INTO price(brand_id, start_date, end_date, rate_id, product_id, priority, final_price, ccy)
VALUES(1, '2020-06-14 00.00.00Z', '2020-12-31 23.59.59Z', 1, 35455, 0, 35.50, 'EUR');

INSERT INTO price(brand_id, start_date, end_date, rate_id, product_id, priority, final_price, ccy)
VALUES(1, '2020-06-14 15.00.00Z', '2020-06-14 18.30.00Z', 2, 35455, 1, 25.45, 'EUR');

INSERT INTO price(brand_id, start_date, end_date, rate_id, product_id, priority, final_price, ccy)
VALUES(1, '2020-06-15 00.00.00Z', '2020-06-15 11.00.00Z', 3, 35455, 1, 30.50, 'EUR');

INSERT INTO price(brand_id, start_date, end_date, rate_id, product_id, priority, final_price, ccy)
VALUES(1, '2020-06-15 16.00.00Z', '2020-12-31 23.59.59Z', 4, 35455, 1, 38.95, 'EUR');