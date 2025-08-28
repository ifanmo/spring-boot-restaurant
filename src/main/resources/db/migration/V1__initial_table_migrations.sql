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
    id         BIGINT primary key ,
    first_name VARCHAR(255) not null,
    last_name  VARCHAR(255) not null,
    address    VARCHAR(255) not null,
    postcode   VARCHAR(10)  not null
);

create table staff
(
    id         BIGINT primary key,
    first_name VARCHAR(255) not null,
    last_name  VARCHAR(255) not null,
    address    VARCHAR(255) null,
    postcode   VARCHAR(255) not null
);

create table bookings
(
    id               BIGINT auto_increment primary key ,
    number_of_guests INT                           not null,
    start_time        DATETIME                      not null,
    end_time          DATETIME                      not null,
    status           VARCHAR(20) default 'PENDING' not null,
    seating_id BIGINT not null
);

create table seating
(
    id              BIGINT auto_increment primary key,
    number_of_seats int                             not null,
    status          VARCHAR(20) default 'AVAILABLE' null
);

create table shifts
(
    id         BIGINT auto_increment
        primary key,
    start_time DATETIME not null,
    end_time   DATETIME not null
);

alter table customers
    add constraint customers_users_id_fk
        foreign key (id) references users (id);

alter table staff
    add constraint staff_users_id_fk
        foreign key (id) references users (id);

alter table bookings
    add customer_id BIGINT not null;

alter table bookings
    add constraint bookings_customers_id_fk
        foreign key (customer_id) references customers (id)
            on delete cascade;

alter table bookings
    add constraint bookings_seating_id_fk
        foreign key (seating_id) references seating (id)
            on delete cascade;

create table staff_shifts
(
    staff_id BIGINT not null,
    shift_id BIGINT not null,
    constraint staff_shifts_pk
        primary key (staff_id, shift_id),
    constraint staff_shifts_shifts_id_fk
        foreign key (shift_id) references shifts (id),
    constraint staff_shifts_staff_id_fk
        foreign key (staff_id) references staff (id)
);

rename table seating to restaurant_tables;

alter table bookings
    modify start_time TIME not null;

alter table bookings
    modify end_time TIME not null;

alter table bookings
    add date DATE not null;

alter table shifts
    modify start_time TIME not null;

alter table shifts
    modify end_time Time null;

alter table shifts
    add date DATE not null;

alter table restaurant_tables
    add constraint restaurant_tables_bookings_id_fk
        foreign key (id) references bookings (id);

alter table bookings
    drop foreign key bookings_seating_id_fk;

alter table bookings
    drop column seating_id;









