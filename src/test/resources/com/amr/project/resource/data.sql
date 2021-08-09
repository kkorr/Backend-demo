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

INSERT INTO address
VALUES (1, 'city_index1', 'house1', 'street1', 1, 1);
INSERT INTO address
VALUES (2, 'city_index2', 'house2', 'street2', 2, 2);
INSERT INTO address
VALUES (3, 'city_index3', 'house3', 'street3', 3, 3);

INSERT INTO user
VALUES (1, 1, 'admin', 30, '1971-02-01 00:00:00', 'name1@mail.ru', 'admin', 1, 'lastname1', '111111', '89269991111', 'name1@mail.ru',1,null,null);
INSERT INTO user
VALUES (2, 1, 'moderator', 30, '1980-02-01 00:00:00', 'name2@mail.ru', 'moderator', 1, 'lastname2', '222222', '89269992222', 'name2@mail.ru',2,null,null);
INSERT INTO user
VALUES (3, 1, 'user', 30, '1990-02-01 00:00:00', 'name3@mail.ru', 'user', 1, 'lastname3', '333333', '89269993333', 'name3@mail.ru',3,null,null);

INSERT INTO role
VALUES ('1', 'ADMIN');
INSERT INTO role
VALUES ('2', 'MODERATOR');
INSERT INTO role
VALUES ('3', 'USER');

INSERT INTO user_role
VALUES ('1', '1');
INSERT INTO user_role
VALUES ('2', '2');
INSERT INTO user_role
VALUES ('3', '3');

INSERT INTO country_city
VALUES (1, 1);
INSERT INTO country_city
VALUES (2, 2);
INSERT INTO country_city
VALUES (3, 3);


