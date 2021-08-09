USE `platform`;

INSERT INTO user
VALUES (1, 30, '10.01.1991', 'name1@mail.ru', 'name1', 1, 'lastname1', '12345', '12345678901', 'name1@mail.ru');
INSERT INTO user
VALUES (2, 29, '10.02.1992', 'name2@mail.ru', 'name2', 2, 'lastname2', '12345', '12345678901', 'name2@mail.ru');
INSERT INTO user
VALUES (3, 28, '10.03.1993', 'name3@mail.ru', 'name3', 3, 'lastname3', '12345', '12345678901', 'name3@mail.ru');

INSERT INTO role
VALUES ('1', 'ROLE_ADMIN');
INSERT INTO role
VALUES ('2', 'ROLE_MODERATOR');
INSERT INTO role
VALUES ('3', 'ROLE_USER');

INSERT INTO user_role
VALUES ('1', '1');
INSERT INTO user_role
VALUES ('2', '2');
INSERT INTO user_role
VALUES ('3', '3');

INSERT INTO address
VALUES ('1', 'city_index1', 'house1', 'street1', 1, 1);
INSERT INTO address
VALUES ('2', 'city_index2', 'house2', 'street2', 2, 2);
INSERT INTO address
VALUES ('3', 'city_index3', 'house3', 'street3', 3, 3);

INSERT INTO city
VALUES ('1', 'city1');
INSERT INTO city
VALUES ('2', 'city2');
INSERT INTO city
VALUES ('3', 'city3');

INSERT INTO country
VALUES ('1', 'country1');
INSERT INTO country
VALUES ('2', 'country2');
INSERT INTO country
VALUES ('3', 'country3');

INSERT INTO country_city
VALUES (1, 1);
INSERT INTO country_city
VALUES (2, 2);
INSERT INTO country_city
VALUES (3, 3);

INSERT INTO item
VALUES (1, 1, 'Описание / Характеристики Product_1', 'Название товара_1', 100, 10.5, 1);
INSERT INTO item
VALUES (2, 2, 'Описание / Характеристики Product_2', 'Название товара_2', 200, 20.5, 2);
INSERT INTO item
VALUES (3, 3, 'Описание / Характеристики Product_3', 'Название товара_3', 300, 30.5, 3);

INSERT INTO category
VALUES (1, 'Категория товара_1');
INSERT INTO category
VALUES (2, 'Категория товара_2');
INSERT INTO category
VALUES (3, 'Категория товара_3');

INSERT INTO item_category
VALUES (1, 1);
INSERT INTO item_category
VALUES (2, 2);
INSERT INTO item_category
VALUES (3, 3);

INSERT INTO shop
VALUES (1, 1, 'Описание/ контактные данные Shop_1', 'Название магазина_1', '12345678901', 1);
INSERT INTO shop
VALUES (2, 2, 'Описание/ контактные данные Shop_2', 'Название магазина_2', '12345678901', 2);
INSERT INTO shop
VALUES (3, 3, 'Описание/ контактные данные Shop_3', 'Название магазина_3', '12345678901', 3);

INSERT INTO shop_item
VALUES (1, 1);
INSERT INTO shop_item
VALUES (2, 2);
INSERT INTO shop_item
VALUES (3, 3);




