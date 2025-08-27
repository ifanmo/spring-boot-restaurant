create table users
(
    id       BIGINT auto_increment
        primary key,
    email    VARCHAR(255)                   not null,
    password VARCHAR(255)                   not null,
    role     VARCHAR(20) default 'CUSTOMER' not null
);

create table customers
(
    id         BIGINT auto_increment primary key ,
    first_name VARCHAR(255) not null,
    last_name  VARCHAR(255) not null,
    address    VARCHAR(255) not null,
    postcode   VARCHAR(10)  not null
);

create table staff
(
    id         BIGINT auto_increment primary key,
    first_name VARCHAR(255) not null,
    last_name  VARCHAR(255) not null,
    address    VARCHAR(255) null,
    postcode   VARCHAR(255) not null
);

create table bookings
(
    id               BIGINT auto_increment primary key ,
    number_of_guests INT                           not null,
    startTime        DATETIME                      not null,
    endTime          DATETIME                      not null,
    status           VARCHAR(20) default 'PENDING' not null
);

create table tables
(
    id              BIGINT auto_increment primary key,
    number_of_seats int                             not null,
    status          VARCHAR(20) default 'AVAILABLE' null
);

create table shifts
(
    id         BIGINT auto_increment
        primary key,
    start_time datetime not null,
    end_time   DATETIME not null
);

create table staff_shifts
(
    id       BIGINT auto_increment
        primary key,
    staff_id BIGINT not null,
    shift_id BIGINT not null
);







