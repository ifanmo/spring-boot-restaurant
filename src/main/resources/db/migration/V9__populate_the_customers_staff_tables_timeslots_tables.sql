INSERT INTO restaurant_tables (capacity)
VALUES (2),
       (2),
       (2),
       (2),
       (4),
       (4),
       (4),
       (8),
       (8),
       (10);

INSERT INTO shifts (start_time, end_time)
VALUES ('08:00:00', '10:00:00'),
       ('10:00:00', '12:00:00'),
       ('12:00:00', '14:00:00'),
       ('14:00:00', '16:00:00'),
       ('16:00:00', '18:00:00'),
       ('18:00:00', '20:00:00'),
       ('20:00:00', '22:00:00'),
       ('22:00:00', '00:00:00');

INSERT INTO time_slots (start_time)
VALUES ('12:00:00'),
       ('13:00:00'),
       ('14:00:00'),
       ('15:00:00'),
       ('16:00:00'),
       ('17:00:00'),
       ('18:00:00'),
       ('19:00:00'),
       ('20:00:00'),
       ('21:00:00'),
       ('22:00:00');

INSERT INTO customers (first_name, last_name, street_number, street, postcode)
VALUES ('Alice', 'Johnson', '12', 'Maple Street', '10001'),
       ('Bob', 'Smith', '89A', 'Oak Avenue', '10002'),
       ('Charlie', 'Davis', '45', 'Pine Road', '10003'),
       ('Diana', 'Evans', '7B', 'Cedar Lane', '10004'),
       ('Ethan', 'Walker', '63', 'Birch Boulevard', '10005'),
       ('Fiona', 'Bennett', '22', 'Willow Drive', '10006'),
       ('George', 'Clark', '88', 'Elm Street', '10007'),
       ('Hannah', 'Miller', '19C', 'Spruce Terrace', '10008'),
       ('Isaac', 'Lewis', '101', 'Chestnut Parkway', '10009'),
       ('Julia', 'Adams', '35', 'Poplar Crescent', '10010');

INSERT INTO staff (id, first_name, last_name)
VALUES (11, 'Liam', 'Anderson'),
       (12, 'Mia', 'Hughes'),
       (13, 'Noah', 'Robinson'),
       (14, 'Olivia', 'Gray'),
       (15, 'Sophia', 'Foster'),
       (16, 'Elijah', 'Barnes'),
       (17, 'Amelia', 'Wells'),
       (18, 'Lucas', 'Knight'),
       (19, 'Ava', 'Jenkins'),
       (20, 'Mason', 'Reynolds');