DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (description, datetime, calories, user_id)
VALUES ('breakfast User', '2022-10-29 07:00', 500, 100000),
       ('lunch User', '2022-10-29 13:00', 1500, 100000),
       ('dinner User', '2022-10-29 17:00', 1000, 100000),
       ('night snack User', '2022-10-29 00:00', 600, 100000),
       ('breakfast 2 User', '2022-10-30 10:00', 500, 100000),
       ('lunch User', '2022-10-30 15:00', 1100, 100000),
       ('dinner User', '2022-10-30 20:00', 300, 100000),
       ('breakfast Admin', '2022-10-25 08:00', 300, 100001),
       ('lunch Admin', '2022-10-25 12:00', 1000, 100001),
       ('dinner Admin', '2022-10-25 19:00', 800, 100001);
