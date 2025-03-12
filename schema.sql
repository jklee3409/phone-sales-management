CREATE TABLE `users` (
  `user_id` integer PRIMARY KEY,
  `name` varchar(40),
  `username` varchar(255) UNIQUE,
  `password` varchar(255),
  `amount` integer
);

CREATE TABLE `phone` (
  `phone_id` integer PRIMARY KEY,
  `model` varchar(50),
  `brand` varchar(50),
  `released_at` date,
  `price` integer,
  `stock` integer
);

CREATE TABLE `phone_detail` (
  `phone_id` integer PRIMARY KEY,
  `processor` varchar(50),
  `ram` varchar(20),
  `storage` varchar(20),
  `battery` integer,
  `weight` integer
);

CREATE TABLE `orders` (
  `order_id` integer PRIMARY KEY,
  `user_id` integer,
  `phone_id` integer,
  `sale_price` integer,
  `order_date` date
);

ALTER TABLE `orders` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

ALTER TABLE `orders` ADD FOREIGN KEY (`phone_id`) REFERENCES `phone` (`phone_id`);

ALTER TABLE `phone_detail` ADD FOREIGN KEY (`phone_id`) REFERENCES `phone` (`phone_id`);
