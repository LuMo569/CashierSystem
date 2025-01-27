-- Create the database for the system
CREATE DATABASE database_cashier_system CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Use the created database
USE database_cashier_system;

-- Table for storing tables in the bar/restaurant
CREATE TABLE tables (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

-- Table for menu items with details like price and category
CREATE TABLE menu_items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    category VARCHAR(50) NOT NULL,
    subcategory VARCHAR(50) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    purchase_price DECIMAL(10, 2) NOT NULL
);

-- Table for orders, linked to tables and waiters
CREATE TABLE orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    table_id INT NOT NULL,
    waiter_id INT NOT NULL,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (table_id) REFERENCES tables(id),
    FOREIGN KEY (waiter_id) REFERENCES account(id)
);

-- Table for items within an order
CREATE TABLE order_items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    menu_item_id INT NOT NULL,
    quantity INT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (menu_item_id) REFERENCES menu_items(id)
);

-- Table for reservation management
CREATE TABLE reservations (
    id INT AUTO_INCREMENT PRIMARY KEY,
    table_name VARCHAR(100) NOT NULL,
    reserved_by VARCHAR(100) NOT NULL,
    reservation_date TIMESTAMP NOT NULL,
    guest_count INT NOT NULL,
    notes TEXT,
    status ENUM('confirmed', 'cancelled', 'completed') DEFAULT 'confirmed'
);

-- Table for unified account system
CREATE TABLE account (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    account_type VARCHAR(50) NOT NULL
);

-- Table for changes made to item price by an admin -- (could be extended)
CREATE TABLE log (
    id INT AUTO_INCREMENT PRIMARY KEY,
    log_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    log_text VARCHAR(100) NOT NULL
);

-- Insert sample data for tables
INSERT INTO tables (name) VALUES
('Table 1'), ('Table 2'), ('Table 3'),
('Table 4'), ('Table 5'), ('Table 6'),
('Table 7'), ('Table 8'), ('Table 9'),
('Table 10'), ('Table 11');

-- Insert sample data for menu items (Drinks)
INSERT INTO menu_items (name, category, subcategory, price, purchase_price) VALUES
('Water 0.3L', 'drink', 'non-alcoholic', 2.00, 1.50),
('Water 0.5L', 'drink', 'non-alcoholic', 2.50, 1.80),
('Sparkling Water 0.3L', 'drink', 'non-alcoholic', 2.50, 1.80),
('Sparkling Water 0.5L', 'drink', 'non-alcoholic', 3.00, 2.00),
('Cola', 'drink', 'non-alcoholic', 2.50, 2.00),
('Sprite', 'drink', 'non-alcoholic', 2.50, 2.00),
('Fanta', 'drink', 'non-alcoholic', 2.50, 2.00),
('Ice Tea Peach', 'drink', 'non-alcoholic', 3.00, 2.20),
('Ice Tea Lemon', 'drink', 'non-alcoholic', 3.00, 2.20),
('Energy Drink', 'drink', 'non-alcoholic', 3.50, 2.50),
('Smoothie', 'drink', 'non-alcoholic', 4.00, 3.00),
('Herbal Tea', 'drink', 'non-alcoholic', 2.00, 1.50),
('Hot Chocolate', 'drink', 'non-alcoholic', 3.50, 2.50),
('Espresso', 'drink', 'non-alcoholic', 2.50, 1.80),
('Cappuccino', 'drink', 'non-alcoholic', 3.50, 2.80),
('Draft Beer', 'drink', 'alcoholic', 4.00, 3.00),
('Wheat Beer', 'drink', 'alcoholic', 4.50, 3.20),
('Aperol Spritz', 'drink', 'alcoholic', 6.00, 4.50),
('Tequila Shot', 'drink', 'alcoholic', 3.00, 2.50),
('Whisky Sour', 'drink', 'alcoholic', 7.00, 5.50),
('Red Wine', 'drink', 'alcoholic', 5.00, 3.80),
('White Wine', 'drink', 'alcoholic', 5.00, 3.80),
('Mojito', 'drink', 'alcoholic', 6.00, 4.50),
('Gin Tonic', 'drink', 'alcoholic', 6.50, 5.00),
('Cosmopol.', 'drink', 'cocktail', 6.50, 5.00),
('Caipirinha', 'drink', 'cocktail', 6.50, 5.00),
('Long Island Iced Tea', 'drink', 'cocktail', 7.00, 5.50),
('Pina Colada', 'drink', 'cocktail', 6.50, 5.00);

-- Insert sample data for menu items (Food)
INSERT INTO menu_items (name, category, subcategory, price, purchase_price) VALUES
('Burger', 'food', 'main food', 8.00, 5.00),
('Burger with Fries', 'food', 'main food', 10.00, 6.50),
('Chicken Wings', 'food', 'main food', 7.00, 4.50),
('Nachos with Cheese', 'food', 'main food', 5.00, 3.00),
('Caesar Salad', 'food', 'main food', 6.00, 4.00),
('Mini Pizzas', 'food', 'snack', 7.50, 5.00),
('Club Sandwich', 'food', 'snack', 6.50, 4.00),
('Cheese Platter', 'food', 'snack', 8.00, 5.50),
('Peanuts', 'food', 'snack', 2.50, 1.80),
('Pretzel Sticks', 'food', 'snack', 3.00, 2.00),
('Chips', 'food', 'snack', 2.50, 1.80);

-- Insert sample data for accounts
INSERT INTO account (username, password, account_type) VALUES
('Alice', 'password123', 'WAITER'),
('Bob', 'securepass', 'WAITER'),
('root', 'root', 'ADMIN');
