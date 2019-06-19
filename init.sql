USE ministack_db;
CREATE TABLE IF NOT EXISTS Users (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(25) NOT NULL,
    `password` VARCHAR(100) NOT NULL,
    `email` VARCHAR(45),
    `authorized` BOOLEAN default false,
    `created` DATETIME,
    `role` VARCHAR(30),
    PRIMARY KEY (`id`)
);
INSERT INTO Users (username, password, email, authorized, created, role) values ('john', '$2a$10$95N8kMMOyOOb29uzTgB0YO0YjMDB4l8b8WMmTVCKdhZQpV9mopchS', 'john@doe.com', true, CURRENT_TIMESTAMP, 'ADMINISTRATOR');
INSERT INTO Users (username, password, email, authorized, created, role) values ('test', '$2a$10$95N8kMMOyOOb29uzTgB0YO0YjMDB4l8b8WMmTVCKdhZQpV9mopchS', 'test@example.com', true, CURRENT_TIMESTAMP, 'USER');
