create table time_slot
(
    id          BIGINT not null
        primary key,
    start_time  TIME   not null
);

alter table bookings
    change booking_time booking_time_slot BIGINT not null;

alter table bookings
    add constraint bookings_time_slot_id_fk
        foreign key (booking_time_slot) references time_slot (id);