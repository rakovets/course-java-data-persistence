CREATE DATABASE restaurant;
USE restaurant;

CREATE TABLE restaurant (
    id INT AUTO_INCREMENT,
    name VARCHAR(40) UNIQUE NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE review (
    id INT AUTO_INCREMENT,
    content TEXT NOT NULL,
    restaurant_id INT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant(id)
);

CREATE TABLE dish (
    id INT AUTO_INCREMENT,
    name VARCHAR(50) UNIQUE NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE restaurant_review (
    restaurant_id INT,
    review_id INT,
    PRIMARY KEY (restaurant_id, review_id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant(id),
    FOREIGN KEY (review_id) REFERENCES review(id)
);

CREATE TABLE restaurant_dish (
    restaurant_id INT,
    dish_id INT,
    PRIMARY KEY (restaurant_id, dish_id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant(id),
    FOREIGN KEY (dish_id) REFERENCES dish(id)
);
