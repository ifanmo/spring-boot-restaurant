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
    first_name VARCHAR(55) not null,
    last_name  VARCHAR(55) not null,
    street_number VARCHAR(10) not null,
    street    VARCHAR(55) not null,
    postcode   VARCHAR(10)  not null
);

create table staff
(
    id         BIGINT auto_increment primary key,
    first_name VARCHAR(55) not null,
    last_name  VARCHAR(55) not null
);

create table bookings
(
    id               BIGINT auto_increment primary key ,
    customer_id BIGINT not null,
    number_of_guests INT not null,
    date          DATE                      not null,
    status VARCHAR(10) default 'PENDING' not null,
    seating_id BIGINT not null
);

create table seating
(
    id              BIGINT auto_increment primary key,
    capacity int                             not null
);

create table shifts
(
    id         BIGINT auto_increment primary key,
    start_time TIME not null,
    date DATE not null
);

create table staff_shifts
(
    staff_id BIGINT not null,
    shift_id BIGINT not null,
    constraint staff_shifts_pk
        primary key (staff_id, shift_id)
);



create table booking_seating
(
    id BIGINT primary key not null,
    booking_id BIGINT,
    seating_id BIGINT not null,
    date DATE not null,
    start_time TIME not null,
    status VARCHAR(20) not null
);

alter table bookings
    add constraint bookings_customers_id_fk
        foreign key (customer_id) references customers (id);

alter table bookings
    add constraint bookings_timeslots_id_fk
        foreign key (seating_id) references seating (id);

alter table customers
    add constraint customers_users_id_fk
        foreign key (id) references users (id);

alter table staff
    add constraint staff_users_id_fk
        foreign key (id) references users (id);

alter table staff_shifts
    add constraint staff_shifts_shifts_id_fk
        foreign key (shift_id) references shifts (id);

alter table staff_shifts
    add constraint staff_shifts_staff_id_fk
        foreign key (staff_id) references staff (id);

alter table booking_seating
    add constraint booking_seating_seating_id_fk
        foreign key (seating_id) references seating (id);

alter table booking_seating
    add constraint booking_seating_bookings_id_fk
        foreign key (booking_id) references bookings(id);
















