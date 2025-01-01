INSERT INTO category (id, name, description) VALUES
(1, 'Electronics', 'Devices and gadgets'),
(2, 'Books', 'Various kinds of books'),
(3, 'Clothing', 'Apparel and accessories');

INSERT INTO product (id, name, description, available_quantity, price, category_id) VALUES
(1, 'Smartphone', 'Latest model smartphone', 50, 699.99, 1),
(2, 'Laptop', 'High performance laptop', 30, 999.99, 1),
(3, 'Novel', 'Bestselling novel', 100, 19.99, 2),
(4, 'T-Shirt', 'Comfortable cotton t-shirt', 200, 9.99, 3);