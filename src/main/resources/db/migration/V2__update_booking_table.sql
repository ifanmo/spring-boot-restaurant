drop table booking_seating;

alter table bookings
    change date booking_date date not null;

alter table bookings
    add booking_time TIME not null after booking_date;

alter table bookings
    change number_of_guests guests int not null after booking_time;

alter table bookings
    modify seating_id bigint not null after customer_id;



alter table bookings
    add created_at TIMESTAMP default CURRENT_TIMESTAMP;

alter table bookings
    drop foreign key bookings_timeslots_id_fk;

alter table bookings
    add constraint bookings_seating_id_fk
        foreign key (seating_id) references seating (id);


